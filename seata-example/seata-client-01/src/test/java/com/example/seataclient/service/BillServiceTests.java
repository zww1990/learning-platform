package com.example.seataclient.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seataclient.domain.Bill;

/**
 * BillServiceTests
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:58:56
 */
@SpringBootTest
public class BillServiceTests {
	@Autowired
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
