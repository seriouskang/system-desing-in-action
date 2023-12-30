package com.example.consumer;

import com.example.domain.Coupon;
import com.example.domain.FailedEvent;
import com.example.repository.CouponRepository;
import com.example.repository.FailedEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCreatedConsumer {
    private final Logger logger = LoggerFactory.getLogger(CouponCreatedConsumer.class);
    private final CouponRepository couponRepository;
    private final FailedEventRepository failedEventRepository;

    public CouponCreatedConsumer(CouponRepository couponRepository, FailedEventRepository failedEventRepository) {
        this.couponRepository = couponRepository;
        this.failedEventRepository = failedEventRepository;
    }

    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(Long userId) {
        try {
            couponRepository.save(new Coupon(userId));
        } catch (Exception e) {
            logger.error("failed to issue coupon: {}", userId);
            failedEventRepository.save(new FailedEvent(userId));
        }
    }
}
