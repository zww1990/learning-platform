package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
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
		return new Docket(DocumentationType.SWAGGER_2).groupName(props.getGroupName()).select()
				.apis(RequestHandlerSelectors.basePackage(props.getBasePackage())).build()
				.apiInfo(new ApiInfoBuilder().contact(new Contact(props.getName(), props.getUrl(), props.getEmail()))
						.title(props.getTitle()).description(props.getDescription()).version(props.getVersion())
						.termsOfServiceUrl(props.getTermsOfServiceUrl()).license(props.getLicense())
						.licenseUrl(props.getLicenseUrl()).build());
	}

}
