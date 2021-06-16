package com.example.demo;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
public class SpringSwaggerV3Application implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(SpringSwaggerV3Application.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringSwaggerV3Application.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@Resource
	private List<HttpMessageConverter<?>> converters;

	@Override
	public void run(String... args) throws Exception {
		this.converters.stream().filter(p -> p instanceof AbstractHttpMessageConverter)
				.forEach(c -> ((AbstractHttpMessageConverter<?>) c).setDefaultCharset(StandardCharsets.UTF_8));
	}
}
