package com.example.seataclient.service;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seataclient.domain.Bill;

@SpringBootTest
public class BillServiceTests {
	@Resource
	private BillService service;

	@Test
	public void testCreate() {
		try {
			Bill bill = new Bill()//
					.setAmount(10D)//
					.setFoodId(1)//
					.setQuantity(2L)//
					.setUserId(1);
			System.err.println(this.service.create(bill).getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
