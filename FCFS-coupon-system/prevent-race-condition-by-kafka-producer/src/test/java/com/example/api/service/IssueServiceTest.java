package com.example.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {
    @Autowired
    private IssueService issueService;

    @Test
    void issue_many_time() throws InterruptedException {
        int issueAttemptCount = 1_000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(issueAttemptCount);

        for(int i=0; i<issueAttemptCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    issueService.issue(userId);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
    }
}