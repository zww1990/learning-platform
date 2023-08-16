package com.example.schedulequartz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SpringScheduleApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		String[] names = this.context.getBeanDefinitionNames();
		for (int i = 0, len = names.length; i < len; i++) {
			System.err.println((i + 1) + "\t" + names[i]);
		}
	}
}
