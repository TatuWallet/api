package br.com.tatuwallet.blockchain;

import br.com.tatuwallet.blockchain.model.NovaCarteira;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;

import java.security.SecureRandom;

import static org.web3j.crypto.Bip32ECKeyPair.HARDENED_BIT;

/**
 * Gera carteiras Ethereum do tipo HD (Hierarchical Deterministic).
 *
 * <p>Uma carteira nada mais é que uma chave privada (segredo) da qual se calcula um
 * endereço público (0x + 40 caracteres hexadecimais). Quem tem a chave privada controla
 * o saldo do endereço; o endereço sozinho só serve para receber e consultar.
 *
 * <p>Como a chave privada é impossível de decorar, o padrão do mercado (BIP-39, BIP-32
 * e BIP-44, o mesmo do MetaMask e do Anvil) parte de uma frase de 12 palavras:
 *
 * <pre>
 * 12 palavras (mnemonic)
 *      | BIP-39: PBKDF2 gera a seed (512 bits)
 *      v
 * chave mestra
 *      | BIP-32/44: caminho m/44'/60'/0'/0/indice
 *      v
 * chave privada --> chave pública --> ENDEREÇO
 * </pre>
 *
 * <p>É determinístico: as mesmas 12 palavras sempre geram os mesmos endereços. Por isso
 * quem tem as palavras controla TODAS as carteiras derivadas delas.
 *
 * <p>Esta classe só faz criptografia local. Não fala com o Anvil nem com o banco, então
 * funciona mesmo com o nó desligado.
 *
 * <p>SEGURANÇA: o servidor nunca guarda o mnemonic nem a chave privada. Só o endereço vai
 * para o banco, e as palavras voltam ao usuário uma única vez, na criação. Nunca logar o mnemonic.
 */
@Component
public class HdWalletGenerator {

    // SecureRandom é o gerador aleatório criptograficamente seguro do Java.
    // Nunca usar java.util.Random aqui: ele é previsível, e quem adivinhasse a sequência
    // descobriria a chave de outras carteiras.
    private final SecureRandom random = new SecureRandom();

    /**
     * Cria uma carteira nova: sorteia 12 palavras e deriva o endereço da primeira conta.
     */
    public NovaCarteira gerar() {
        // 1) Entropia: 16 bytes = 128 bits aleatórios. É daqui que nasce o mnemonic.
        //    (128 bits + 4 de checksum = 132 bits, e 132 / 11 bits por palavra = 12 palavras.
        //    Com 32 bytes seriam 24 palavras.)
        byte[] entropia = new byte[16];
        random.nextBytes(entropia);

        // 2) Entropia -> palavras, usando a lista padrão BIP-39 de 2048 palavras.
        String mnemonic = MnemonicUtils.generateMnemonic(entropia);

        // 3) Endereço da conta de índice 0 (a primeira) dessas palavras.
        return new NovaCarteira(derivar(mnemonic, 0), mnemonic);
    }

    /**
     * Devolve o endereço de uma conta específica de um mnemonic.
     *
     * @param mnemonic as 12 palavras
     * @param indice   número da conta (0, 1, 2...). O mesmo mnemonic gera um endereço
     *                 diferente para cada índice. As 10 contas do Anvil são os índices
     *                 0 a 9 do mnemonic "test test ... junk".
     */
    public String derivar(String mnemonic, int indice) {
        // 1) Palavras -> seed de 512 bits (PBKDF2 com 2048 iterações, padrão BIP-39).
        //    O "" é a passphrase opcional (a "25ª palavra"). Se alguém usasse uma, as
        //    mesmas 12 palavras gerariam carteiras totalmente diferentes.
        byte[] seed = MnemonicUtils.generateSeed(mnemonic, "");

        // 2) Seed -> chave mestra, a raiz da árvore de chaves (BIP-32).
        Bip32ECKeyPair mestre = Bip32ECKeyPair.generateKeyPair(seed);

        // 3) Caminho dentro da árvore: m / 44' / 60' / 0' / 0 / indice
        //    44'    -> padrão BIP-44 (estrutura de carteiras multi-moeda)
        //    60'    -> tipo de moeda: 60 = Ethereum (lista SLIP-44)
        //    0'     -> número da conta
        //    0      -> 0 = endereços de recebimento (1 seria "troco", usado no Bitcoin)
        //    indice -> qual endereço desta conta
        //    O apóstrofo (') significa derivação "hardened": o "| HARDENED_BIT" liga o bit
        //    mais alto do número. Isso impede que uma chave pública vazada permita
        //    descobrir as chaves dos outros níveis.
        int[] caminho = {44 | HARDENED_BIT, 60 | HARDENED_BIT, 0 | HARDENED_BIT, 0, indice};

        // 4) Percorre o caminho a partir da chave mestra e chega na chave da conta.
        //    "filho" contém a chave privada, mas é só uma variável local: ela não é guardada
        //    nem devolvida, e some quando o método termina.
        Bip32ECKeyPair filho = Bip32ECKeyPair.deriveKeyPair(mestre, caminho);

        // 5) Chave -> endereço: o web3j calcula a chave pública e pega os últimos 20 bytes do
        //    hash Keccak-256 dela (o formato de endereço do Ethereum), com o prefixo 0x.
        //    Guardamos em minúsculas porque o padrão EIP-55 mistura maiúsculas e minúsculas
        //    como checksum, e padronizar evita tratar o mesmo endereço como dois diferentes.
        return Credentials.create(filho).getAddress().toLowerCase();
    }
}