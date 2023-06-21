package com.example.democonsumer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

@SpringBootTest
public class ConfigControllerTests {
	@Resource
	private ConfigController controller;

	@Test
	public void testStudent() {
		try {
			System.err.println(this.controller.student());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
