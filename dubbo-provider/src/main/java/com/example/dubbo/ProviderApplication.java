package com.example.dubbo;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProviderApplication {
	private static final Logger log = LoggerFactory.getLogger(ProviderApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProviderApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
		RabbitTemplate template = context.getBean(RabbitTemplate.class);
		String exchange = "ex.demo";
		template.convertAndSend(exchange, "rk.demo1", Arrays.asList("这是来自rk.demo1的消息"));
		template.convertAndSend(exchange, "rk.demo2", Arrays.asList("这是来自rk.demo2的消息"));
		log.info("消息已发送");
	}
}
