package io.example.c3p0.springboot;

import io.gitlab.zww1990.c3p0.springboot.autoconfigure.C3p0DataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(C3p0DataSourceAutoConfigure.class)
@Slf4j
public class C3p0SpringBootStarterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(C3p0SpringBootStarterApplication.class, args);
        log.info("Get Bean Definition Count = {}", context.getBeanDefinitionCount());
//		java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
    }

}
