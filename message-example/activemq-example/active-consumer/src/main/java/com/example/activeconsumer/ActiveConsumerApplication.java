package com.example.activeconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 消费者启动类
 * 
 * @author zhang weiwei
 * @since 2023年8月6日,下午8:50:06
 */
@SpringBootApplication
@Slf4j
public class ActiveConsumerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ActiveConsumerApplication.class, args);
		log.info("Get Bean Definition Count = {}", context.getBeanDefinitionCount());
//		java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

}
