package com.example.test.service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * RetryService
 * 
 * @author zhangweiwei
 * @date 2021年6月17日,下午1:06:44
 */
public interface RetryService {
	/**
	 * doRetry
	 * 
	 * @param times
	 * @author zhangweiwei
	 * @date 2021年6月17日,下午1:08:18
	 */
	void doRetry(AtomicInteger times);

	/**
	 * doRetryForBackOff
	 * 
	 * @param times
	 * @author zhangweiwei
	 * @date 2021年6月17日,下午1:26:19
	 */
	void doRetryForBackOff(AtomicInteger times);

	/**
	 * doRetryTemplate
	 * 
	 * @param times
	 * @author zhangweiwei
	 * @date 2021年6月17日,下午1:46:04
	 */
	void doRetryTemplate(AtomicInteger times);
}
