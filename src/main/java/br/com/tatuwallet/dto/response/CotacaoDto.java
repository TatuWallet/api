package br.com.tatuwallet.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

public record CotacaoDto (
    String simbolo,
    BigDecimal precoBrl,
    Instant atualizadoEm
) {}
