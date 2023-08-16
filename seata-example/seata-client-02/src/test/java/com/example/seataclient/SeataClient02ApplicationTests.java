package com.example.seataclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * SeataClient02ApplicationTests
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午5:06:52
 */
@SpringBootTest
public class SeataClient02ApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		System.err.println(this.context.getBeanDefinitionCount());
	}

}
