package com.example.service;

import com.example.domain.Coupon;
import com.example.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    private final CouponRepository couponRepository;

    public IssueService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public void issue(Long userId) {
        long count = couponRepository.count();

        if(count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
