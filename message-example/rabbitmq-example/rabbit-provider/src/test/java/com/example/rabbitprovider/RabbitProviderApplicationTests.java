package com.example.rabbitprovider;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class RabbitProviderApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		RabbitTemplate template = this.context.getBean(RabbitTemplate.class);
		System.err.println(template.getMessageConverter().getClass());
		RabbitAdmin admin = this.context.getBean(RabbitAdmin.class);
		System.err.println(admin.isAutoStartup());
	}

}
