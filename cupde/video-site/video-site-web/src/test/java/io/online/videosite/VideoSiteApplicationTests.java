package io.online.videosite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

@SpringBootTest
public class VideoSiteApplicationTests {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testContextLoads() {
		System.err.println(this.applicationContext.getBeanDefinitionCount());
		Stream.of(this.applicationContext.getBeanDefinitionNames())
				.forEach(System.err::println);
	}

}
