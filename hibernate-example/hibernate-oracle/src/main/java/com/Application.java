package com;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}
	
	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}
}
