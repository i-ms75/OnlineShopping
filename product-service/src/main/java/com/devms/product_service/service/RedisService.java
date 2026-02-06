package com.devms.product_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void saveToRedis(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        log.info("Saved {} to redis with key {}", value, key);
    }
    public String getFromRedis(String key) {
        Object name= redisTemplate.opsForValue().get(key);
        return (String) name;
    }
}
