package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class SwaggerKnife4jApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SwaggerKnife4jApplication.class, args);
        log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
    }

    private List<HttpMessageConverter<?>> converters;

    @Override
    public void run(String... args) throws Exception {
        this.converters.stream().filter(p -> p instanceof AbstractHttpMessageConverter)
                .forEach(c -> ((AbstractHttpMessageConverter<?>) c).setDefaultCharset(StandardCharsets.UTF_8));
    }
}
