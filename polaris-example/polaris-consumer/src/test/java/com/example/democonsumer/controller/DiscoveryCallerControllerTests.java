package com.example.democonsumer.controller;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
