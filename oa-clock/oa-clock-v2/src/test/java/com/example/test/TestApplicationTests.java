package com.example.test;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.example.test.controller.TestController;
import com.example.test.service.TestService;

@SpringBootTest
public class TestApplicationTests {
	@Resource
	private ApplicationContext context;
	@Resource
	private TestController controller;
	@Resource
	private TestService service;

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
	public void testIndex() {
		try {
			Object value = this.controller.index();
			Assertions.assertNotNull(value);
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
<<<<<<<< HEAD:oa-clock/oa-clock-v1/src/test/java/com/example/test/TestApplicationTests.java
	
========

>>>>>>>> t2:oa-clock-v2/src/test/java/com/example/test/TestApplicationTests.java
}
