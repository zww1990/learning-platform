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
			Food food = new Food()//
					.setFoodId(2)//
					.setFoodName("玉芝琳变通")//
					.setStock(123L);
			System.err.println(this.controller.update(food));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
