package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SwaggerProperties.class)
public class RootConfig {

}
