package com.example.api.service;

import com.example.api.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {
    @Autowired
    private IssueService issueService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    void issue_one_time() {
        // given, when
        issueService.issue(1L);

        // then
        long issuedCount = couponRepository.count();
        assertThat(issuedCount).isEqualTo(1);
    }
}