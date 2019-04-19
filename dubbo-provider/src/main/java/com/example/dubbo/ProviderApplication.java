package com.example.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProviderApplication {
	private static final Logger log = LoggerFactory.getLogger(ProviderApplication.class);

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(ProviderApplication.class, args);
		context.start();
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
		System.in.read();
	}
}
