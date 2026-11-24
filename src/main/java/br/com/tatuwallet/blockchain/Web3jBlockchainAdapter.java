package br.com.tatuwallet.blockchain;

import br.com.tatuwallet.exception.BlockchainException;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import java.io.IOException;
import java.math.BigInteger;

@Component
public class Web3jBlockchainAdapter implements BlockchainPort {

    private final Web3j web3j;

    public Web3jBlockchainAdapter(Web3j web3j) {
        this.web3j = web3j;
    }

    @Override
    public BigInteger consultarSaldoWei(String endereco) {
        try {
            EthGetBalance resposta = web3j
                    .ethGetBalance(endereco, DefaultBlockParameterName.LATEST)
                    .send();
            if (resposta.hasError()) {
                throw new BlockchainException("Erro do nó: " + resposta.getError().getMessage());
            }
            return resposta.getBalance();
        } catch (IOException e) {
            throw new BlockchainException("Não foi possível falar com o nó blockchain", e);
        }
    }
}