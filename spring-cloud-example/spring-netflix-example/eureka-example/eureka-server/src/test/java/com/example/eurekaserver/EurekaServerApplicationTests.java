package com.example.eurekaserver;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.PropertyResolver;
import org.springframework.web.context.support.StandardServletEnvironment;

import io.undertow.websockets.jsr.WebSocketDeploymentInfo;

@SpringBootTest
public class EurekaServerApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		String[] names = this.context.getBeanDefinitionNames();
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			System.err.println((i + 1) + "\t" + name);
		}
	}

	@Test
	public void testWebSocketDeploymentInfo() {
		try {
			Class<?> cls = new WebSocketDeploymentInfo().getClass();
			System.err.println(cls.getName());
			System.err.println(cls.getCanonicalName());
			System.err.println(cls.getPackageName() + '.' + cls.getSimpleName());
			System.err.println(cls.getTypeName());
			System.err.println(WebSocketDeploymentInfo.ATTRIBUTE_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindFirstNonLoopbackAddress() {
		try {
			PropertyResolver resolver = this.context.getBean(PropertyResolver.class);
			System.err.println(IdUtils.getDefaultInstanceId(resolver));
			System.err.println(IdUtils.getResolvedServiceId(resolver));
			InetUtils inetUtils = this.context.getBean(InetUtils.class);
			System.err.println(inetUtils.findFirstNonLoopbackAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPropertyResolver() {
		try {
			StandardServletEnvironment resolver = (StandardServletEnvironment) this.context
					.getBean(PropertyResolver.class);
			resolver.getPropertySources().forEach(ps -> {
				if (ps.getSource() instanceof Map m) {
					System.err.println("***************************************" + ps.getName()
							+ "******************************************");
					m.entrySet().forEach(System.err::println);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
