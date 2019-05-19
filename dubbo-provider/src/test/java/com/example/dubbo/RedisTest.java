package com.example.dubbo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dubbo.config.RootConfig;
import com.example.dubbo.model.CallTask;
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
	public void testSingleThread() {
		try {
			long begin = System.currentTimeMillis();
			List<User> users = (List<User>) this.redisTemplate.boundValueOps("users").get();
			List<User> result = new ArrayList<>();
			for (User user : users) {
				if (user.getName().contains("520")) {
					result.add(user);
				}
			}
			System.err.println("姓名中包含520的元素总数=" + result.size());
			long end = System.currentTimeMillis();
			System.err.println("单线程查询耗时(毫秒)=" + (end - begin));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMultiThread() {
		try {
			long begin = System.currentTimeMillis();
			List<User> users = (List<User>) this.redisTemplate.boundValueOps("users").get();
			ForkJoinPool pool = new ForkJoinPool();
			Future<List<User>> future = pool.submit(new CallTask(users, 0, users.size()));
			System.err.println("姓名中包含520的元素总数=" + future.get().size());
			pool.shutdown();
			long end = System.currentTimeMillis();
			System.err.println("多线程查询耗时(毫秒)=" + (end - begin));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
