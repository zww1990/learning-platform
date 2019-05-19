package com.example.dubbo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dubbo.config.RootConfig;
import com.example.dubbo.model.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@SuppressWarnings("unchecked")
public class RedisTest {
	@Resource
	private ApplicationContext context;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void initData() {
		try {
			List<User> users = new ArrayList<>();
			for (int i = 1; i <= 6000; i++) {
				users.add(new User(1000 + i, "张三" + i, 11, "男"));
			}
			this.redisTemplate.boundValueOps("users").set(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelect() {
		try {
			List<User> users = (List<User>) this.redisTemplate.boundValueOps("users").get();
			for (User user : users) {
				System.err.println(user.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
