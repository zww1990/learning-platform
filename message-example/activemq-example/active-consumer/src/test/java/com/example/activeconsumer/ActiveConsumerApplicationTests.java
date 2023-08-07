package com.example.activeconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

@SpringBootTest
public class ActiveConsumerApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		System.err.println(jmsTemplate.getMessageConverter());
		JmsMessagingTemplate messagingTemplate = context.getBean(JmsMessagingTemplate.class);
		System.err.println(messagingTemplate.getMessageConverter());
		System.err.println(messagingTemplate.getJmsMessageConverter());
	}

}
