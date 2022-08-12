package io.example.demo;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * ApplicationContextTests
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午8:44:21
 */
@SpringBootTest
public class ApplicationContextTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		try {
			System.err.println(this.context.getBeanDefinitionCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
