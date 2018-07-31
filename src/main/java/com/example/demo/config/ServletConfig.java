package com.example.demo.config;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
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
@EnableWebMvc
@MultipartConfig
@ComponentScan("com.example.demo.controller")
public class ServletConfig extends WebMvcConfigurerAdapter {
	@Resource
	private SwaggerProperties swaggerProps;

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof StringHttpMessageConverter) {
				StringHttpMessageConverter shmc = (StringHttpMessageConverter) converter;
				shmc.setDefaultCharset(Charset.forName("UTF-8"));
			} else if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter mjhmc = (MappingJackson2HttpMessageConverter) converter;
				mjhmc.setObjectMapper(this.jsonObjectMapper());
			}
		}
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new UserInfoHandlerMethodArgumentResolver());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (this.swaggerProps.isEnable()) {
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
	}

	@Bean
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
	}

	@Bean
	public ObjectMapper jsonObjectMapper() {
		return Jackson2ObjectMapperBuilder.json().timeZone(TimeZone.getTimeZone("Asia/Shanghai")).build();
	}

	@Configuration
	@EnableSwagger2
	@Conditional(SwaggerCondition.class)
	public static class SwaggerAutoConfig {
		@Bean
		public Docket api(SwaggerProperties props) {
			Docket docket = new Docket(DocumentationType.SWAGGER_2);
			return docket.apiInfo(this.apiInfo(props));
		}

		private ApiInfo apiInfo(SwaggerProperties props) {
			Contact contact = new Contact(props.getName(), props.getUrl(), props.getEmail());
			return new ApiInfo(props.getTitle(), props.getDescription(), props.getVersion(),
					props.getTermsOfServiceUrl(), contact, props.getLicense(), props.getLicenseUrl(),
					Collections.emptyList());
		}
	}

	public static class SwaggerCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("swagger.enable", boolean.class);
		}
	}
}
