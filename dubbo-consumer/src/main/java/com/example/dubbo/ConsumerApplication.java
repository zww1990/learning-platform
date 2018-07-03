package com.example.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.dubbo.action.BarAction;

@SpringBootApplication
@EnableRabbit
public class ConsumerApplication {
	private static final Logger log = LoggerFactory.getLogger(ConsumerApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
		BarAction action = context.getBean(BarAction.class);
		action.exec("5i5j");
	}
}
