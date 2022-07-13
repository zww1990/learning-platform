package com.example.demo.test;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.RetryService;

@SpringBootTest
public class RetryServiceTests {
	@Resource
	private RetryService retryService;

	@Test
	public void testUseRetryable() {
		try {
			this.retryService.useRetryable(new AtomicInteger(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUseRetryableAndBackoff() {
		try {
			this.retryService.useRetryableAndBackoff(new AtomicInteger(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUseRetryTemplate() {
		try {
			this.retryService.useRetryTemplate(new AtomicInteger(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
