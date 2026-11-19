package br.com.tatuwallet.dto.response;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Timestamp;

import java.time.Instant;
import java.util.UUID;

public record UserDto(
        UUID id,
        String name,
        String username,
        String email,
        Instant dataCadastro,
        Boolean active
) {}