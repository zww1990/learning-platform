package com.example.dubbo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.dubbo.action.BarAction;
import com.example.dubbo.config.AppConfig;

public class ConsumerMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.start();
		BarAction action = context.getBean(BarAction.class);
		action.exec("5i5j");
	}
}
