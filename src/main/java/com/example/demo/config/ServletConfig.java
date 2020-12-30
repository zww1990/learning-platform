package com.example.demo.config;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.boot.starter.autoconfigure.SwaggerUiWebMvcConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.oas.configuration.OpenApiDocumentationConfiguration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.demo.controller")
public class ServletConfig implements WebMvcConfigurer {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof AbstractHttpMessageConverter) {
				AbstractHttpMessageConverter<?> hmc = (AbstractHttpMessageConverter<?>) converter;
				hmc.setDefaultCharset(StandardCharsets.UTF_8);
			}
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter mjhmc = (MappingJackson2HttpMessageConverter) converter;
				mjhmc.setObjectMapper(this.objectMapper());
			}
		}
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new UserInfoHandlerMethodArgumentResolver(this.objectMapper()));
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding(StandardCharsets.UTF_8.name());
		return resolver;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return Jackson2ObjectMapperBuilder.json().timeZone(TimeZone.getTimeZone("Asia/Shanghai")).build();
	}

	@Configuration
	@EnableOpenApi
	@Import({ OpenApiDocumentationConfiguration.class, Swagger2DocumentationConfiguration.class,
			SwaggerUiWebMvcConfiguration.class })
	@Conditional(SwaggerCondition.class)
	public static class SwaggerAutoConfig {
		@Bean
		public Docket api(SwaggerProperties props) {
			Contact contact = new Contact(props.getName(), props.getUrl(), props.getEmail());
			ApiInfo apiInfo = new ApiInfo(props.getTitle(), props.getDescription(), props.getVersion(),
					props.getTermsOfServiceUrl(), contact, props.getLicense(), props.getLicenseUrl(),
					Collections.emptyList());
			return new Docket(DocumentationType.OAS_30).select()
					.apis(RequestHandlerSelectors.basePackage(props.getBasePackage())).build().apiInfo(apiInfo);
		}
	}

	public static class SwaggerCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("swagger.enable", boolean.class);
		}
	}
}
