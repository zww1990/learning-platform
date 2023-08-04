package com.example.polarisprovider.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigControllerTests {
	@Autowired
	private ConfigController controller;

	@Test
	public void testTeacher() {
		try {
			System.err.println(this.controller.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
