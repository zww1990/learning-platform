package io.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.support.RetryTemplate;

import io.example.demo.service.RetryService;
import jakarta.annotation.Resource;

@SpringBootTest
public class RetryTemplateTests {
	@Resource
	private ApplicationContext context;
	@Resource
	private RetryService retryService;

	@Test
	public void testRetry() {
		try {
			System.err.println(this.context.getBean(RetryTemplate.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDoRetry() {
		try {
			this.retryService.doRetry(new AtomicInteger(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDoRetryForBackOff() {
		try {
			this.retryService.doRetryForBackOff(new AtomicInteger(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDoRetryTemplate() {
		try {
			this.retryService.doRetryTemplate(new AtomicInteger(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
