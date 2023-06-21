package com.example.demoprovider;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import jakarta.annotation.Resource;

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
