package br.com.tatuwallet.blockchain;

import java.math.BigInteger;

public interface BlockchainPort {
    BigInteger consultarSaldoWei(String endereco);
}
