package com.example.demo.web.config;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan("com.example.demo.web")
@EnableWebMvc
@EnableSwagger2
public class DispatcherConfig extends WebMvcConfigurerAdapter {
	@Resource
	private ObjectMapper objectMapper;

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof StringHttpMessageConverter) {
				StringHttpMessageConverter shmc = (StringHttpMessageConverter) converter;
				shmc.setDefaultCharset(Charset.defaultCharset());
			}
		}
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new UserInfoHandlerMethodArgumentResolver(this.objectMapper));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		String title = "我的标题";
		String description = "我的描述";
		String version = "我的版本";
		String termsOfServiceUrl = "服务条款的网址";
		String name = "我的名字";
		String url = "我的网址";
		String email = "我的邮箱";
		Contact contact = new Contact(name, url, email);
		String license = "我的许可证";
		String licenseUrl = "我的许可证网址";
		return new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl,
				Collections.emptyList());
	}
}
