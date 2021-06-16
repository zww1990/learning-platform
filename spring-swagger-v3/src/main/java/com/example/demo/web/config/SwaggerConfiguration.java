package com.example.demo.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "springfox.documentation.enabled", havingValue = "true")
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {

	@Bean
	public OpenAPI openAPI(SwaggerProperties props) {
		return new OpenAPI()
				.info(new Info()
						.contact(new Contact().email(props.getEmail()).name(props.getName()).url(props.getUrl()))
						.title(props.getTitle()).description(props.getDescription()).version(props.getVersion())
						.license(new License().name(props.getLicense()).url(props.getLicenseUrl()))
						.termsOfService(props.getTermsOfServiceUrl()))
				.externalDocs(new ExternalDocumentation().description(props.getDescription()).url(props.getUrl()));
	}
}
