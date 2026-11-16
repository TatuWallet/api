package br.com.tatuwallet.dto.request;

import jakarta.validation.constraints.*;

public record CriarUsuarioRequest(
        @NotBlank @Size(min = 3, max = 30)
        @Pattern(regexp = "^[a-zA-Z0-9_]+$") String username,
        @NotBlank @Size(min = 3, max = 30) String name,
        @NotBlank @Email @Size(max = 120) String email
) {}