package com.example.dubbo;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.BoundValueOperations;
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

	@Test
	public void testRedis() {
		try {
			RedisTemplate<String, Object> temp = this.context.getBean(RedisTemplate.class);
			User a1 = new User(1001, "张三", 11, "男");
			BoundValueOperations<String, Object> ops = temp.boundValueOps("users");
			System.err.println(ops.get());
			ops.set(Arrays.asList(a1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
