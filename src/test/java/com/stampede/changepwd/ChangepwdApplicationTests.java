package com.stampede.changepwd;

import java.util.Arrays;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class ChangepwdApplicationTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		try {
			System.err.println(this.context.getBeanDefinitionCount());
			Arrays.stream(this.context.getBeanDefinitionNames()).forEach(System.err::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
