package com.example.mybatisflex.mapper;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mybatisflex.entity.Account;

import jakarta.annotation.Resource;

@SpringBootTest
public class AccountMapperTests {
	@Resource
	private AccountMapper accountMapper;

	@Test
	public void testSave() {
		try {
			System.err.println(this.accountMapper
					.insert(new Account().setAge(12).setBirthday(LocalDateTime.now()).setUserName("张三")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelect() {
		try {
			System.err.println(this.accountMapper.selectAll().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
