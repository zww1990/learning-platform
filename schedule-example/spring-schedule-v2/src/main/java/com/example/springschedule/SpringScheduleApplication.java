package com.example.springschedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.springschedule.config.SpringConfig;

public class SpringScheduleApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringScheduleApplication.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		context.registerShutdownHook();
		context.start();
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
		Thread.currentThread().join();
	}
}
