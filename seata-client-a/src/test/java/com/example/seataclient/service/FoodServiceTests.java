package com.example.seataclient.service;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seataclient.domain.Food;

@SpringBootTest
public class FoodServiceTests {
	@Resource
	private FoodService service;

	@Test
	public void testUpdate() {
		try {
			Food food = new Food()//
					.setFoodId(2)//
					.setFoodName("玉芝琳变通胶囊")//
					.setStock(666L);
			System.err.println(this.service.update(food));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
