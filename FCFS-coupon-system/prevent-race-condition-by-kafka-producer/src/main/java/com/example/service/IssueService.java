package com.example.service;

import com.example.producer.CouponCreateProducer;
import com.example.repository.CouponCountRepository;
import com.example.repository.CouponIssuedUserRepository;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final CouponIssuedUserRepository couponIssuedUserRepository;

    public IssueService(CouponCountRepository couponCountRepository,
                        CouponCreateProducer couponCreateProducer,
                        CouponIssuedUserRepository couponIssuedUserRepository) {
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.couponIssuedUserRepository = couponIssuedUserRepository;
    }

    public void issue(Long userId) {
        Long issued = couponIssuedUserRepository.add(userId);
        if(issued != 1) {
            return;
        }

        long count = couponCountRepository.increment();

        if(count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }
}
