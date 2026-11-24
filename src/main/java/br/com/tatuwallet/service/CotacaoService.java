package br.com.tatuwallet.service;

import br.com.tatuwallet.cache.CachePort;
import br.com.tatuwallet.dto.response.CotacaoDto;
import br.com.tatuwallet.entity.PrecoHistorico;
import br.com.tatuwallet.repository.PrecoHistoricoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CotacaoService {

    private static final String KEY_PREFIX = "cotacao:";

    private final CachePort cache;
    private final PrecoHistoricoRepository precoHistoricoRepository;
    private final ObjectMapper objectMapper;

    @Value("${tatu.cache.ttl-cotacao:60s}")
    private Duration ttlCotacao;

    public CotacaoService(CachePort cache,
                          PrecoHistoricoRepository precoHistoricoRepository,
                          ObjectMapper objectMapper) {
        this.cache = cache;
        this.precoHistoricoRepository = precoHistoricoRepository;
        this.objectMapper = objectMapper;
    }

    public CotacaoDto obterCotacao(String simbolo) {
        String par = simbolo.toUpperCase();
        String key = KEY_PREFIX + par;

        var hit = cache.get(key, CotacaoDto.class);
        if (hit.isPresent()) {
            return hit.get();
        }

        CotacaoDto cotacao = buscarCotacaoNaFonte(par);

        try {
            String json = objectMapper.writeValueAsString(cotacao);
            cache.put(key, json, ttlCotacao);
        } catch (JsonProcessingException e) {
        }

        return cotacao;
    }

    private CotacaoDto buscarCotacaoNaFonte(String simbolo) {
        PrecoHistorico preco = precoHistoricoRepository
                .findFirstByCriptomoedaSimboloOrderByColetadoEmDesc(simbolo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cotacao nao encontrada para " + simbolo));

        return new CotacaoDto(
                simbolo,
                preco.getPrecoBrl(),
                preco.getColetadoEm() != null ? preco.getColetadoEm() : Instant.now()
        );
    }

}
