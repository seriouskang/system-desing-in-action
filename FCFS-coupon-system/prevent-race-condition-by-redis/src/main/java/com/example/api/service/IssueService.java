package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public IssueService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    public void issue(Long userId) {
        Long count = couponCountRepository.increment();
        if(count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
