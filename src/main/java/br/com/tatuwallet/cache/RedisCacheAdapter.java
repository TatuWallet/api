package br.com.tatuwallet.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.Optional;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheAdapter implements CachePort {

    private final StringRedisTemplate redis;
    private final ObjectMapper objectMapper;

    public RedisCacheAdapter(StringRedisTemplate redis, ObjectMapper objectMapper) {
        this.redis = redis;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> Optional<T> get(String key, Class<T> type) {
        try {
            String json = redis.opsForValue().get(key);
            if (json == null) {
                return Optional.empty();
            }
            return Optional.of(objectMapper.readValue(json, type));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void put(String chave, String valor, Duration ttl) {
        try {
            String json = objectMapper.writeValueAsString(valor);
            redis.opsForValue().set(chave, json, ttl);
        } catch (Exception e) {
        }
    }

    @Override
    public void evict(String key) {
        redis.delete(key);
    }

}
