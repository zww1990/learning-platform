package com.example.demo.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.root.RootConfig;
import com.example.demo.web.config.ServletConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { RootConfig.class, ServletConfig.class })
@WebAppConfiguration
public class AppTest {
	@Resource
	private ApplicationContext context;

	@Test
	public void testLoad() {
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
