package io.example.iviewui;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class IviewuiExampleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(IviewuiExampleApplication.class, args);
		log.info("Spring Bean Definition Count = {}", context.getBeanDefinitionCount());
//		printBeans(context);
	}

	static void printBeans(ApplicationContext context) {
		Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

}
