package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * spring boot application
 * 
 * @author zhang weiwei
 * @date 2021年5月25日,下午4:16:15
 */
@SpringBootApplication
@Slf4j
public class HelloApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HelloApplication.class, args);
		log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

}
