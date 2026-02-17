package com.hrudhay.kyc_api.customer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class SessionCacheService {

    private final StringRedisTemplate redis;
    private final ObjectMapper objectMapper;

    public SessionCacheService(StringRedisTemplate redis, ObjectMapper objectMapper) {
        this.redis = redis;
        this.objectMapper = objectMapper;
    }

    private String userKey(String email) { return "user:" + email.toLowerCase(); }

    public void cacheUser(String email, Object userDto) {
        try {
            String json = objectMapper.writeValueAsString(userDto);
            redis.opsForValue().set(userKey(email), json, Duration.ofMinutes(30));
        } catch (Exception e) {
            throw new RuntimeException("Failed to cache user", e);
        }
    }

    public String getCachedUserJson(String email) {
        return redis.opsForValue().get(userKey(email));
    }
}
