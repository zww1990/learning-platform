package com.example.demo.web.config;

import java.util.Collections;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "springfox.documentation.enabled", havingValue = "true")
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {
	@Bean
	public Docket api(SwaggerProperties props) {
		return new Docket(DocumentationType.OAS_30).select()
				.apis(RequestHandlerSelectors.basePackage(props.getBasePackage())).build().apiInfo(this.apiInfo(props));
	}

	private ApiInfo apiInfo(SwaggerProperties props) {
		Contact contact = new Contact(props.getName(), props.getUrl(), props.getEmail());
		return new ApiInfo(props.getTitle(), props.getDescription(), props.getVersion(), props.getTermsOfServiceUrl(),
				contact, props.getLicense(), props.getLicenseUrl(), Collections.emptyList());
	}

}
