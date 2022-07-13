package com.example.demoprovider.controller;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigControllerTests {
	@Resource
	private ConfigController controller;

	@Test
	public void testPerson() {
		try {
			System.err.println(this.controller.person());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
