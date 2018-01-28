package com.example.demo.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.example.demo.SpringSwaggerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringSwaggerApplication.class })
public class SpringSwaggerApplicationTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void contextLoads() {
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
			RequestMappingHandlerAdapter bean = this.context.getBean(RequestMappingHandlerAdapter.class);
			List<HttpMessageConverter<?>> list = bean.getMessageConverters();
			for (HttpMessageConverter<?> mc : list) {
				if (mc instanceof StringHttpMessageConverter) {
					StringHttpMessageConverter shmc = (StringHttpMessageConverter) mc;
					System.err.println(shmc.getDefaultCharset());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
