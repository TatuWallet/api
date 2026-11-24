package br.com.tatuwallet.exception;

public class CarteiraNaoEncontradaException extends RuntimeException {

    public CarteiraNaoEncontradaException(Long id) {
        super("Carteira não encontrada: " + id);
    }

    public CarteiraNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}