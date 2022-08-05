package com.example.springschedule;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * Spring Schedule Application Tests
 * 
 * @author zhang weiwei
 * @since 2022年8月5日,下午8:19:03
 */
@SpringBootTest
public class SpringScheduleApplicationTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
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
