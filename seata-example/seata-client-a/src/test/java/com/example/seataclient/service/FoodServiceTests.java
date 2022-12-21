package com.example.seataclient.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seataclient.domain.Food;

/**
 * FoodServiceTests
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:59:01
 */
@SpringBootTest
public class FoodServiceTests {
	@Autowired
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
