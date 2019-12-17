package com.yupaits.sample.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yupaits
 * @date 2019/7/11
 */
@RestController
public class RedisSampleController {
    private static final String OK = "ok";
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisSampleController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/set")
    public String setData(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return OK;
    }

    @GetMapping("/get")
    public String getData(@RequestParam String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
