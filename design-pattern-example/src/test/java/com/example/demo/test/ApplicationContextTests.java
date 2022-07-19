package com.example.demo.test;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class ApplicationContextTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		try {
			System.err.println(this.context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
