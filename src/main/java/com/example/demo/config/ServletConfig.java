package com.example.demo.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.databind.ObjectMapper;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.demo.controller")
public class ServletConfig implements WebMvcConfigurer {
	@Resource
	private SwaggerProperties swaggerProps;

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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (this.swaggerProps.isEnable()) {
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
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
	@EnableSwagger2
	@Conditional(SwaggerCondition.class)
	public static class SwaggerAutoConfig {
		@Bean
		public Docket createRestApi(SwaggerProperties props) {
			Docket docket = new Docket(DocumentationType.SWAGGER_2);
			ApiInfoBuilder builder = new ApiInfoBuilder().title(props.getTitle()).description(props.getDescription())
					.contact(new Contact(props.getName(), props.getUrl(), props.getEmail()))
					.version(props.getVersion());
			return docket.apiInfo(builder.build()).groupName(props.getTitle());
		}
	}

	public static class SwaggerCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("swagger.enable", boolean.class);
		}
	}
}
