package com.example.security;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityApplicationTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		try {
			String[] names = this.context.getBeanDefinitionNames();
			for (int i = 0; i < names.length; i++) {
				String name = names[i];
				System.err.println(String.format("%s\t%s", i + 1, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
