package com.demo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.demo.entity.User;

@SpringBootTest
public class UserMapperTests {
	@Autowired
	private UserMapper userDao;

	@Test
	public void testSave() {
		try {
			System.err.println(this.userDao.insert(
					new User().setId(System.currentTimeMillis()).setAge(18).setName("张三").setEmail("abc@123.com")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			System.err.println(this.userDao.selectList(Wrappers.emptyWrapper()).size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
