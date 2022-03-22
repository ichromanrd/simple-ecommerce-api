package com.blockchainspace.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Service
public class CacheService {

    @Value("${blockchainspace.cache.redis.ttl}")
    private int timeToLive;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void put(String key, String value, int ttl) {
        redisTemplate.opsForValue().set(key, value, Duration.of(ttl, ChronoUnit.SECONDS));
    }

    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value, Duration.of(timeToLive, ChronoUnit.SECONDS));
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void clear(String key) {
        redisTemplate.delete(key);
    }

}
