package com.example.seataclient.mapper;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seataclient.domain.Account;

/**
 * AccountMapperTests
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:59:09
 */
@SpringBootTest
public class AccountMapperTests {
	@Resource
	private AccountMapper mapper;

	@Test
	public void testInsert() {
		try {
			Account acc = new Account();
			acc.setBalance(12345.67);
			acc.setUserName("李四");
			System.err.println(this.mapper.insert(acc));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			Account acc = this.mapper.selectByUserId(2);
			acc.setBalance(123.45D);
			acc.setUserName("王五");
			System.err.println(this.mapper.update(acc));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectByUserId() {
		try {
			System.err.println(this.mapper.selectByUserId(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
