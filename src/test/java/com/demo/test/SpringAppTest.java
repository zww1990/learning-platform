package com.demo.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.Application;
import com.demo.dao.mapper.UserDynamicSqlSupport;
import com.demo.dao.mapper.UserMapper;
import com.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringAppTest {
	@Resource
	private ApplicationContext context;

	@Test
	public void testContextLoad() {
		try {
			String[] names = this.context.getBeanDefinitionNames();
			for (int i = 0, len = names.length; i < len; i++) {
				System.err.println((i + 1) + "\t" + names[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Resource
	private UserMapper userMapper;
	@Resource
	private ObjectMapper objectMapper;

	@Test
	public void testMapper() {
		try {
			List<User> users = this.userMapper.selectByExample()
					.where(UserDynamicSqlSupport.id, SqlBuilder.isIn(1, 2, 3))
					.and(UserDynamicSqlSupport.age, SqlBuilder.isBetween(11).and(22))
					.and(UserDynamicSqlSupport.name, SqlBuilder.isLikeCaseInsensitive("%张%"))
					.orderBy(UserDynamicSqlSupport.birthday.descending(), UserDynamicSqlSupport.id).build().execute();
			System.err.println(this.objectMapper.writeValueAsString(users));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
