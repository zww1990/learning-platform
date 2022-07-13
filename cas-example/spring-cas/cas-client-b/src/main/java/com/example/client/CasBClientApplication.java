package com.example.client;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = { ProjectInfoAutoConfiguration.class, ValidationAutoConfiguration.class,
		PersistenceExceptionTranslationAutoConfiguration.class, RedisReactiveAutoConfiguration.class,
		MultipartAutoConfiguration.class, SpringDataWebAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
		TaskSchedulingAutoConfiguration.class })
public class CasBClientApplication {
	private static final Logger log = LoggerFactory.getLogger(CasBClientApplication.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(CasBClientApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@EnableCasClient
	@ConditionalOnProperty(prefix = "cas", name = { "server-url-prefix", "server-login-url", "client-host-url" })
	@ConditionalOnWebApplication
	public static class CasClientAutoConfig {
	}
}
