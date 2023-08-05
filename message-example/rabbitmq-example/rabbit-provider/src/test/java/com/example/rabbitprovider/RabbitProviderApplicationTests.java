package com.example.rabbitprovider;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.example.provider.api.domain.Hello;

@SpringBootTest
public class RabbitProviderApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		RabbitTemplate template = this.context.getBean(RabbitTemplate.class);
//		template.setConfirmCallback((correlationData, ack, cause) -> {
//			System.err.println("重新设置一个confirm回调");
//		});
//		template.setReturnsCallback(returned -> {
//			System.err.println("重新设置一个returns回调");
//		});
		System.err.println(template.getMessageConverter());
		RabbitTemplateCustomizer customizer = this.context.getBean(RabbitTemplateCustomizer.class);
		System.err.println(customizer);
	}

	@Test
	public void testSendMessage() {
		RabbitTemplate template = this.context.getBean(RabbitTemplate.class);
		template.convertAndSend("ex.hello", "rk.hello1", new Hello());
		System.err.println("done");
	}

	@Test
	public void testSendMessage2() {
		RabbitTemplate template = this.context.getBean(RabbitTemplate.class);
		template.convertAndSend("ex.hello1", "rk.hello", new Hello(), new CorrelationData());
		System.err.println("done");
	}

	@Test
	public void testSendMessage3() {
		RabbitTemplate template = this.context.getBean(RabbitTemplate.class);
		template.convertAndSend("ex.hello", "rk.hello1", new Hello(), new CorrelationData());
		System.err.println("done");
	}

	@Test
	public void testSendMessage4() {
		RabbitTemplate template = this.context.getBean(RabbitTemplate.class);
		template.convertAndSend("ex.hello", "rk.hello", new Hello(), new CorrelationData());
		System.err.println("done");
	}
}
