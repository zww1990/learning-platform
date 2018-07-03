package com.example.client.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.client.web")
public class ServletConfig extends WebMvcConfigurerAdapter {
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof StringHttpMessageConverter) {
				StringHttpMessageConverter shmc = (StringHttpMessageConverter) converter;
				shmc.setDefaultCharset(Charset.defaultCharset());
			}
		}
	}

}
