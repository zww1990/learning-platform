package com.example.test.controller;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.test.model.UserLogin;

@SpringBootTest
public class HelloControllerTests {
	@Resource
	private HelloController controller;

	@Test
	public void testIndex() {
		try {
			System.err.println(this.controller.index(new UserLogin().setUserNo("6666").setPassword("123456")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIndex2() {
		try {
			System.err.println(this.controller.index(new UserLogin().setUserNo("9999").setPassword("123456")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
