package io.example.demo.service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * RetryService
 * 
 * @author zhangweiwei
 * @since 2021年6月17日,下午1:06:44
 */
public interface RetryService {
	/**
	 * doRetry
	 * 
	 * @param times 次数
	 * @author zhangweiwei
	 * @since 2021年6月17日,下午1:08:18
	 */
	void doRetry(AtomicInteger times);

	/**
	 * doRetryForBackOff
	 * 
	 * @param times 次数
	 * @author zhangweiwei
	 * @since 2021年6月17日,下午1:26:19
	 */
	void doRetryForBackOff(AtomicInteger times);

	/**
	 * doRetryTemplate
	 * 
	 * @param times 次数
	 * @author zhangweiwei
	 * @since 2021年6月17日,下午1:46:04
	 */
	void doRetryTemplate(AtomicInteger times);
}
