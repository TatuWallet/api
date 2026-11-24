package br.com.tatuwallet.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ImportarCarteiraRequest(
        @NotNull UUID idUsuario,
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Pattern(regexp = "^0x[a-fA-F0-9]{40}$") String endereco
) {}