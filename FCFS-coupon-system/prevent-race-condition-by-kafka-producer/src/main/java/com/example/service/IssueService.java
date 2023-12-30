package com.example.service;

import com.example.producer.CouponCreateProducer;
import com.example.repository.CouponCountRepository;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;

    public IssueService(CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void issue(Long userId) {
        long count = couponCountRepository.increment();

        if(count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }
}
