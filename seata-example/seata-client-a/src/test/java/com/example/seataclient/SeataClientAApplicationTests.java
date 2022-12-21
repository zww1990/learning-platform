package com.example.seataclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * SeataClientAApplicationTests
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:58:48
 */
@SpringBootTest
public class SeataClientAApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		System.err.println(this.context.getBeanDefinitionCount());
	}

}
