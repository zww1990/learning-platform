package com.example.seataclient;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * SeataClientCApplicationTests
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午5:11:13
 */
@SpringBootTest
public class SeataClientCApplicationTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		System.err.println(this.context.getBeanDefinitionCount());
	}

}
