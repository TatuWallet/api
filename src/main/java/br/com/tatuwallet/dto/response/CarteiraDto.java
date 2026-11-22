package br.com.tatuwallet.dto.response;

import br.com.tatuwallet.Enum.TipoCarteira;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CarteiraDto(
        Long id,
        String nome,
        String endereco,
        String rede,
        BigDecimal saldoFiat,
        UUID idUsuario,
        Instant dataCriacao,
        TipoCarteira tipo,
        Boolean verified
) {}
