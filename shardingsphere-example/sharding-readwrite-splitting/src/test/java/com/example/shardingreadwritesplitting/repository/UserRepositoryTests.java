package com.example.shardingreadwritesplitting.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shardingreadwritesplitting.domain.User;

import jakarta.annotation.Resource;

@SpringBootTest
public class UserRepositoryTests {
	@Resource
	private UserRepository userDao;

	@Test
	public void testInsertUser() {
		try {
			User user = new User();
			user.setBirthday(LocalDate.now());
			user.setNickname("张三");
			user.setPassword("123456");
			user.setSex(1);
			int row = this.userDao.insertUser(user);
			System.err.println(row);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectUsers() {
		try {
			List<User> users = this.userDao.selectUsers();
			System.err.println(users.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
