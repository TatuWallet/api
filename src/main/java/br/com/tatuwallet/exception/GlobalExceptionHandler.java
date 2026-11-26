package br.com.tatuwallet.exception;

import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.OptimisticLockingFailureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QueryTimeoutException.class)
    public ResponseEntity<String> tratarTimeout(QueryTimeoutException ex) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("O banco de dados demorou para responder. Tente novamente...");
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<String> tratarConflitoDeVersao(
            OptimisticLockingFailureException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Esta carteira foi modificada por outra operação. Atualize os dados e tente novamente.");
    }
}