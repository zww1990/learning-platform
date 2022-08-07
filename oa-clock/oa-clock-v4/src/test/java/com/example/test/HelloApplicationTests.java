package com.example.test;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.example.hello.HelloApplication;
import com.example.hello.service.HelloService;

/**
 * HelloApplicationTests
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午4:25:31
 */
@SpringBootTest(classes = HelloApplication.class)
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

}
