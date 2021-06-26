package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

import lombok.extern.slf4j.Slf4j;

/**
 * spring boot application
 * 
 * @author zhangweiwei
 * @date 2021年5月25日,下午4:16:15
 */
@EnableRetry
@SpringBootApplication
@Slf4j
public class TestApplication {

	/**
	 * @param args
	 * @author zhangweiwei
	 * @date 2021年5月25日,下午4:16:28
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);
		log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
	}

}
