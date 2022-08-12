package com.example.hello;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * SchedulerTests
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午1:34:14
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SchedulerTests {
	@Resource
	private Scheduler scheduler;

	@Test
	public void testScheduler() {
		try {
			System.err.println(this.scheduler.isStarted());
			System.err.println(this.scheduler.getCurrentlyExecutingJobs());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
