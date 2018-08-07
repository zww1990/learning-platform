package com.demo;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.demo.config.SpringConfig;

public class Application {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		context.registerShutdownHook();
		context.start();
		System.err.println(context.getBeanDefinitionCount());
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}
}
