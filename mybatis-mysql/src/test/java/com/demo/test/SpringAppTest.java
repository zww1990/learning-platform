package com.demo.test;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SpringAppTest {
	@Resource
	private ApplicationContext context;

	@Test
	public void testContextLoad() {
		try {
			String[] names = this.context.getBeanDefinitionNames();
			for (int i = 0, len = names.length; i < len; i++) {
				System.err.println((i + 1) + "\t" + names[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
