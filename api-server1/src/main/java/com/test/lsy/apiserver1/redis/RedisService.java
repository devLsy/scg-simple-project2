package com.test.lsy.apiserver1.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveToRedis(Long key, Object value) {
        redisTemplate.opsForValue().set(key.toString(), value, 60, TimeUnit.SECONDS);
    }

    public Object getFromRedis(Long key) {
        return redisTemplate.opsForValue().get(key.toString());
    }
}
