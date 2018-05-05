package com.example.dubbo;

import java.util.Arrays;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.dubbo.config.RootConfig;

public class ProviderMain {
	public static void main(String[] args) {
		try {
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
			context.start();
			RabbitTemplate template = context.getBean(RabbitTemplate.class);
			String exchange = "ex.demo";
			template.convertAndSend(exchange, "rk.demo1", Arrays.asList("这是来自rk.demo1的消息"));
			template.convertAndSend(exchange, "rk.demo2", Arrays.asList("这是来自rk.demo2的消息"));
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
