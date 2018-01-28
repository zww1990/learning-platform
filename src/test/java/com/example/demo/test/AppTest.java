package com.example.demo.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartResolver;

import com.example.demo.SpringSwaggerApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringSwaggerApplication.class })
public class AppTest {
	@Resource
	private ApplicationContext context;

	@Test
	public void testLoad() {
		try {
			ObjectMapper mapper = this.context.getBean(ObjectMapper.class);
			System.err.println(mapper);
			MultipartResolver resolver = this.context.getBean(MultipartResolver.class);
			System.err.println(resolver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
