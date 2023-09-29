package io.example.statemachine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class StatemachineExampleApplicationTests {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testContextLoads() {
		System.err.println("*********************************************************");
		this.applicationContext.getBeansOfType(RedisTemplate.class).forEach((k, v) -> {
			System.err.println("*************" + k + " = " + v + "****************");
			System.err.println(v.getDefaultSerializer());
			System.err.println(v.getKeySerializer());
			System.err.println(v.getValueSerializer());
			System.err.println(v.getHashKeySerializer());
			System.err.println(v.getHashValueSerializer());
		});
		System.err.println("*********************************************************");
		System.err.println(this.applicationContext.getBean(ObjectMapper.class));
	}

}
