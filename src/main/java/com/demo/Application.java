package com.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.demo.config.SpringConfig;

public class Application {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		context.registerShutdownHook();
		context.start();
		System.err.println(context.getBeanDefinitionCount());
		synchronized (Application.class) {
			while (true) {
				try {
					Application.class.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
