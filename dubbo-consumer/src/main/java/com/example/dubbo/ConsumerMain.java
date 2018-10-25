package com.example.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.dubbo.action.BarAction;
import com.example.dubbo.config.RootConfig;

public class ConsumerMain {
	private static final Logger log = LoggerFactory.getLogger(ConsumerMain.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
			log.info("工厂中定义的bean数量={}", context.getBeanDefinitionCount());
			// Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
			context.getBean(BarAction.class).exec("5i5j");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
