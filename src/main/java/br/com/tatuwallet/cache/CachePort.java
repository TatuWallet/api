package br.com.tatuwallet.cache;

import java.time.Duration;
import java.util.Optional;


public interface CachePort {
    <T> Optional<T> get(String key, Class<T> type);
    void put(String chave, String valor, Duration ttl);
    void evict(String chave);
}
