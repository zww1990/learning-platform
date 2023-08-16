package com.example.springschedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Spring Schedule Application Tests
 * 
 * @author zhang weiwei
 * @since 2022年8月5日,下午8:19:03
 */
@SpringBootTest
public class SpringScheduleApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		try {
			System.err.println(this.context.getBeanDefinitionCount());
			System.err.println(this.context.getBean(ObjectMapper.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
