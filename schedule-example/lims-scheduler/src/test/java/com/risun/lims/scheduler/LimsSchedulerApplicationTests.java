package com.risun.lims.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;

@SpringBootTest
public class LimsSchedulerApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads() {
//		String[] beanDefinitionNames = this.context.getBeanDefinitionNames();
//		System.err.println(beanDefinitionNames.length);
//		Arrays.stream(beanDefinitionNames).forEach(System.err::println);
		Map<String, DataSource> beans = this.context.getBeansOfType(DataSource.class);
		System.err.println(beans);
	}

}
