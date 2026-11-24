package br.com.tatuwallet.exception;

import java.util.UUID;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(UUID id) {
        super("Usuário não encontrado: " + id);
    }

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}