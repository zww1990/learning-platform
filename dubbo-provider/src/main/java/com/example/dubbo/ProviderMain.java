package com.example.dubbo;

import java.io.IOException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.dubbo.config.AppConfig;

public class ProviderMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.start();
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
