package com.example.dubbo;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.dubbo.config.RootConfig;

public class ConsumerMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
		context.start();
//		BarAction action = context.getBean(BarAction.class);
//		action.exec("5i5j");
		System.err.println(context.getBeanDefinitionCount());
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}
}
