package com.risun.lims.scheduler;

import com.alibaba.druid.filter.Filter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LimsSchedulerApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		String[] beanDefinitionNames = this.context.getBeanDefinitionNames();
		System.err.println(beanDefinitionNames.length);
		Arrays.stream(beanDefinitionNames).forEach(System.err::println);
	}

	@Test
	public void testGetBeansOfType() {
//		Map<String, DataSource> beans = this.context.getBeansOfType(DataSource.class);
		Map<String, Filter> beans = this.context.getBeansOfType(Filter.class);
		System.err.println(beans);
	}
}
