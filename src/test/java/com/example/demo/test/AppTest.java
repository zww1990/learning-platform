package com.example.demo.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.example.demo.root.AppConfig;
import com.example.demo.web.config.DispatcherConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AppConfig.class, DispatcherConfig.class })
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
