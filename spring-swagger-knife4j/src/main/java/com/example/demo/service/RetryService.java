package com.example.demo.service;

import java.util.concurrent.atomic.AtomicInteger;

public interface RetryService {
	void useRetryable(AtomicInteger times);

	void useRetryableAndBackoff(AtomicInteger times);

	void useRetryTemplate(AtomicInteger times);
}
