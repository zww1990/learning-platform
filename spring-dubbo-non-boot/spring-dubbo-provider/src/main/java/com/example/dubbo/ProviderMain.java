package com.example.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.dubbo.config.RootConfig;

public class ProviderMain {
	private static final Logger log = LoggerFactory.getLogger(ProviderMain.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
			context.registerShutdownHook();
			context.start();
			log.info("工厂中定义的bean数量={}", context.getBeanDefinitionCount());
//			java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
