package com.example.rabbitprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Rabbit生产者启动类
 * 
 * @author zhang weiwei
 * @since 2023年8月4日,下午1:27:24
 */
@SpringBootApplication
@Slf4j
public class RabbitProviderApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RabbitProviderApplication.class, args);
		log.info("Get Bean Definition Count = {}", context.getBeanDefinitionCount());
//		java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

}
