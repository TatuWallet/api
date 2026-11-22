package br.com.tatuwallet.dto.response;

import br.com.tatuwallet.Enum.TipoCarteira;
import br.com.tatuwallet.entity.User;

import java.math.BigDecimal;
import java.time.Instant;

public record CarteiraDto(
        Long id,
        String nome,
        BigDecimal saldoFiat,
        User usuario,
        String endereco,
        Instant dataCriacao,
        String rede,
        TipoCarteira tipo,
        Boolean verified
) {
}
