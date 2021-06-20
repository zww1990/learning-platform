package com.example.client.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC配置类
 * 
 * @author home
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.example.client.web")
public class ServletConfig implements WebMvcConfigurer {
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof AbstractHttpMessageConverter) {
				AbstractHttpMessageConverter<?> hmc = (AbstractHttpMessageConverter<?>) converter;
				hmc.setDefaultCharset(StandardCharsets.UTF_8);
			}
		}
	}

}
