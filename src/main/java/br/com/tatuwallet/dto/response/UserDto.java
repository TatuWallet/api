package br.com.tatuwallet.dto.response;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Timestamp;

public record UserDto(@NotBlank String username, @NotBlank String email, @NotEmpty Timestamp data_cadastro) {
}
