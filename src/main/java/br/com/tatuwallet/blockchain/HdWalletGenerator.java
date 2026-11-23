package br.com.tatuwallet.blockchain;

import br.com.tatuwallet.blockchain.model.NovaCarteira;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;

import java.security.SecureRandom;

import static org.web3j.crypto.Bip32ECKeyPair.HARDENED_BIT;

// gerado por IA, por não entender de poha nenhuma de carteira de cripto moeda
// Classe para gerar um endereço d
@Component
public class HdWalletGenerator {

    private final SecureRandom random = new SecureRandom();

    public NovaCarteira gerar() {
        byte[] entropia = new byte[16];                    // 128 bits = 12 palavras
        random.nextBytes(entropia);
        String mnemonic = MnemonicUtils.generateMnemonic(entropia);
        return new NovaCarteira(derivar(mnemonic, 0), mnemonic);
    }

    public String derivar(String mnemonic, int indice) {
        byte[] seed = MnemonicUtils.generateSeed(mnemonic, "");
        Bip32ECKeyPair mestre = Bip32ECKeyPair.generateKeyPair(seed);
        int[] caminho = {44 | HARDENED_BIT, 60 | HARDENED_BIT, 0 | HARDENED_BIT, 0, indice};
        Bip32ECKeyPair filho = Bip32ECKeyPair.deriveKeyPair(mestre, caminho);
        return Credentials.create(filho).getAddress().toLowerCase();
    }
}