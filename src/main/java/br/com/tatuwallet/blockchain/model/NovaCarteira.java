package br.com.tatuwallet.blockchain.model;

public record NovaCarteira(String endereco, String mnemonic) {

    // o mnemonic nunca pode aparecer em log
    @Override
    public String toString() {
        return "NovaCarteira[endereco=" + endereco + ", mnemonic=****]";
    }
}