package br.com.tatuwallet.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarNomeCarteiraRequest(

        @NotBlank(message = "O nome não pode ficar vazio")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome

) {}