package io.example.demo.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import io.example.demo.service.RetryService;
import lombok.extern.slf4j.Slf4j;

/**
 * RetryServiceImpl
 * 
 * @author zhangweiwei
 * @since 2021年6月17日,下午1:08:51
 */
@Service
@Slf4j
public class RetryServiceImpl implements RetryService {

	@Override
	@Retryable(retryFor = Exception.class, maxAttempts = 4)
	public void doRetry(AtomicInteger times) {
		log.info("当前调用次数: {}", times.incrementAndGet());
		throw new RuntimeException("调用方法发生异常(doRetry): " + times.get());
	}

	@Recover
	public void doRecover(Exception ex) {
		log.info(ex.getLocalizedMessage(), ex);
	}

	@Override
	@Retryable(retryFor = Exception.class, maxAttempts = 4, backoff = @Backoff(value = 3000))
	public void doRetryForBackOff(AtomicInteger times) {
		log.info("当前调用次数: {}", times.incrementAndGet());
		throw new RuntimeException("调用方法发生异常(doRetryForBackOff): " + times.get());
	}

	@Override
	public void doRetryTemplate(AtomicInteger times) {
		RetryTemplate template = RetryTemplate.builder().maxAttempts(4).fixedBackoff(3000).retryOn(Exception.class)
				.build();
		template.execute(ctx -> {
			log.info("当前调用次数: {}", times.incrementAndGet());
			throw new RuntimeException("调用方法发生异常(doRetryTemplate): " + times.get());
		}, ctx -> {
			Throwable ex = ctx.getLastThrowable();
			log.info(ex.getLocalizedMessage(), ex);
			return null;
		});
	}
}
