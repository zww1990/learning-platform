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
			System.err.println(this.service.update(new Food(2, "玉芝琳变通胶囊", 666L)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
