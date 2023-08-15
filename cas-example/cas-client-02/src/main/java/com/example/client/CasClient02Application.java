package com.example.client;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CasClient02Application {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(CasClient02Application.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@EnableCasClient
	@ConditionalOnProperty(prefix = "cas", name = { "server-url-prefix", "server-login-url", "client-host-url" })
	@ConditionalOnWebApplication
	public static class CasClientAutoConfig {
	}
}
