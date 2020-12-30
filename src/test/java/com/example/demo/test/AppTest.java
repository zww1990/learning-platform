package com.example.demo.test;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class AppTest {
	@Resource
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		try {
			String[] names = this.context.getBeanDefinitionNames();
			for (int i = 0; i < names.length; i++) {
				System.err.println((i + 1) + "\t" + names[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
