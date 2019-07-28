package com.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.reactor.core.ReactorCoreAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = { ProjectInfoAutoConfiguration.class, ValidationAutoConfiguration.class,
		PersistenceExceptionTranslationAutoConfiguration.class, RedisReactiveAutoConfiguration.class,
		ReactorCoreAutoConfiguration.class, MultipartAutoConfiguration.class, SpringDataWebAutoConfiguration.class,
		TaskExecutionAutoConfiguration.class, TaskSchedulingAutoConfiguration.class })
public class CasClientApplication {
	private static final Logger log = LoggerFactory.getLogger(CasClientApplication.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(CasClientApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}
}
