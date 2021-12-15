package com.example.seataclient.controller;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seataclient.domain.Food;

@SpringBootTest
public class FoodControllerTests {
	@Resource
	private FoodController controller;

	@Test
	public void testUpdate() {
		try {
			System.err.println(this.controller.update(new Food(2, "玉芝琳变通", 123L)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
