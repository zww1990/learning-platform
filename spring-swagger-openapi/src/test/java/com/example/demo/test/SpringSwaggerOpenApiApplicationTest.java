package com.example.demo.test;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootTest
public class SpringSwaggerOpenApiApplicationTest {
	@Resource
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		try {
			String[] names = this.context.getBeanDefinitionNames();
			for (int i = 0; i < names.length; i++) {
				System.err.println((i + 1) + "\t" + names[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHttpMessageConverter() {
		try {
			this.context.getBeansOfType(HttpMessageConverter.class).values().forEach(c -> {
				AbstractHttpMessageConverter<?> mc = (AbstractHttpMessageConverter<?>) c;
				System.err.println(mc.getClass() + "\t" + mc.getDefaultCharset());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
