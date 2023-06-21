package com.example.democonsumer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

@SpringBootTest
public class DiscoveryCallerControllerTests {
	@Resource
	private DiscoveryCallerController controller;

	@Test
	public void testFeign() {
		try {
			System.err.println(this.controller.feign(1, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRest() {
		try {
			System.err.println(this.controller.rest());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
