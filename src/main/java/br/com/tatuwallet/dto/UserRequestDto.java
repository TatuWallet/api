package br.com.tatuwallet.dto;
import java.sql.Timestamp;

import jakarta.validation.constraints.*;

public record UserRequestDto(

        // gerado por IA!!, basicamente temos o not blank onde é anotação jakarta de validaçao para o username não ser Branco
        // Size, refere justamente o tamanho do varchar que você colocou no sgbd
        // pattern as regras de quais letras podem ser aceitadas como user
        @NotBlank(message = "Username é obrigatório")
        @Size(min = 3, max = 30, message = "Username deve ter entre 3 e 30 caracteres")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Use apenas letras, números e _")
        String username,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 120)
        String email
) {}