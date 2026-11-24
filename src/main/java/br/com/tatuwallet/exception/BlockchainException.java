package br.com.tatuwallet.exception;

public class BlockchainException extends RuntimeException {

    public BlockchainException(String mensagem) {
        super(mensagem);
    }

    public BlockchainException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}