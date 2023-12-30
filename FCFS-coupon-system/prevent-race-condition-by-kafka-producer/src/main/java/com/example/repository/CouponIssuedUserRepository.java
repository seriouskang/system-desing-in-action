package com.example.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponIssuedUserRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public CouponIssuedUserRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long add(Long userId) {
        return redisTemplate.
                opsForSet()
                .add("coupon_issued_user", userId.toString());
    }
}
