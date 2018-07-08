package com.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CasClientApplication extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(CasClientApplication.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(CasClientApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CasClientApplication.class);
	}
}
