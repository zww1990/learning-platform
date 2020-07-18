package com.example.demo.web.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class DispatcherConfig implements WebMvcConfigurer {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof AbstractHttpMessageConverter) {
				AbstractHttpMessageConverter<?> hmc = (AbstractHttpMessageConverter<?>) converter;
				hmc.setDefaultCharset(StandardCharsets.UTF_8);
			}
		}
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(this.userInfoHandlerMethodArgumentResolver());
	}

	@Bean
	public UserInfoHandlerMethodArgumentResolver userInfoHandlerMethodArgumentResolver() {
		return new UserInfoHandlerMethodArgumentResolver();
	}
}
