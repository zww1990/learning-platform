package com.example.test;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.example.hello.service.HelloService;

@SpringBootTest
public class HelloApplicationTests {
	@Resource
	private ApplicationContext context;
	@Resource
	private HelloService service;

	@Test
	public void contextLoads() {
		try {
			int count = this.context.getBeanDefinitionCount();
			Assertions.assertTrue(count > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testSayHello() {
		try {
			Object value = this.service.sayHello();
			Assertions.assertNotNull(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
