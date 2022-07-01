package com.example.demo;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class PolarisProviderApplicationTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void testGetBeanDefinitionCount() {
		try {
			System.err.println(this.context.getBeanDefinitionCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
