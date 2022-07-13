package com.example.demo.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.service.RetryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RetryServiceImpl implements RetryService {

	@Override
	@Retryable(value = Exception.class, maxAttempts = 3)
	public void useRetryable(AtomicInteger times) {
		log.info("当前调用次数: {}", times.incrementAndGet());
		throw new RuntimeException("调用方法(useRetryable)发生异常: " + times.get());
	}

	@Recover
	public void useRecover(Exception ex) {
		log.info(ex.getLocalizedMessage(), ex);
	}

	@Override
	@Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(value = 3000))
	public void useRetryableAndBackoff(AtomicInteger times) {
		log.info("当前调用次数: {}", times.incrementAndGet());
		throw new RuntimeException("调用方法(useRetryableAndBackoff)发生异常: " + times.get());
	}

	@Override
	public void useRetryTemplate(AtomicInteger times) {
		RetryTemplate template = RetryTemplate.builder().maxAttempts(3).fixedBackoff(3000).retryOn(Exception.class)
				.build();
		template.execute(ctx -> {
			log.info("当前调用次数: {}", times.incrementAndGet());
			throw new RuntimeException("调用方法(useRetryTemplate)发生异常: " + times.get());
		}, ctx -> {
			Throwable ex = ctx.getLastThrowable();
			log.info(ex.getLocalizedMessage(), ex);
			return null;
		});
	}

}
