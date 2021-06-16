package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringSwaggerKnife4jApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringSwaggerKnife4jApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringSwaggerKnife4jApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}
}
