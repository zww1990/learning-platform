package com.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.demo.config.SpringConfig;

public class Application {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		context.registerShutdownHook();
		context.start();
		System.err.println(context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
		Thread.currentThread().join();
	}
}
