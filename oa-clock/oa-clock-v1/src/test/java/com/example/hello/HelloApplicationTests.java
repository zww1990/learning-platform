package com.example.hello;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * HelloApplicationTests
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午9:17:03
 */
@SpringBootTest
public class HelloApplicationTests {
	@Resource
	private ApplicationContext context;

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
