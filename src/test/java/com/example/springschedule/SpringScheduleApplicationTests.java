package com.example.springschedule;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringScheduleApplicationTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void contextLoads() {
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
