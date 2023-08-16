package com.example.seataclient.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seataclient.domain.Food;

/**
 * FoodControllerTests
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午5:06:41
 */
@SpringBootTest
public class FoodControllerTests {
	@Autowired
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
