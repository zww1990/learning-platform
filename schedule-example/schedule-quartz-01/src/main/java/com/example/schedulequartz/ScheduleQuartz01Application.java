package com.example.schedulequartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class ScheduleQuartz01Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ScheduleQuartz01Application.class, args);
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
        log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
    }

}
