package com.example.sentinelclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SentinelClient01ApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		System.err.println(this.context.getBeanDefinitionCount());
	}

}
