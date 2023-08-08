package io.example.retry;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.example.retry.service.RetryService;

@SpringBootTest
public class RetryTemplateTests {
	@Autowired
	private RetryService retryService;

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
