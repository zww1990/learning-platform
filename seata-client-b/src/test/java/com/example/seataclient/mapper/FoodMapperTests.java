package com.example.seataclient.mapper;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seataclient.domain.Food;

@SpringBootTest
public class FoodMapperTests {
	@Resource
	private FoodMapper mapper;

	@Test
	public void testInsert() {
		try {
			Food food = new Food();
			food.setFoodName("二锅头");
			food.setStock(100L);
			System.err.println(this.mapper.insert(food));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			Food food = this.mapper.selectByFoodId(2);
			food.setFoodName("火腿肠");
			food.setStock(666L);
			System.err.println(this.mapper.update(food));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectByFoodId() {
		try {
			System.err.println(this.mapper.selectByFoodId(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
