package com.example.seataclient.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seataclient.domain.Bill;

/**
 * BillMapperTests
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午5:11:18
 */
@SpringBootTest
public class BillMapperTests {
	@Autowired
	private BillMapper mapper;

	@Test
	public void testInsert() {
		try {
			Bill bill = new Bill()//
					.setAmount(20D)//
					.setBillNum(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")//
							.format(LocalDateTime.now()))//
					.setFoodId(1)//
					.setOrderTime(LocalDateTime.now())//
					.setQuantity(5L)//
					.setUserId(1);
			this.mapper.insert(bill);
			System.err.println(bill.getBillId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
