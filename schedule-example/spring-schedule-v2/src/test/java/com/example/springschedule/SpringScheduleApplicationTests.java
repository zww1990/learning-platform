package com.example.springschedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

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
			System.err.println(this.context.getBean(RestTemplateBuilder.class));
			System.err.println(this.context.getBean(RestTemplate.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
