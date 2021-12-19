package com.example.seataclient;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * SeataClientAApplicationTests
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:58:48
 */
@SpringBootTest
public class SeataClientAApplicationTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		System.err.println(this.context.getBeanDefinitionCount());
	}

}
