package io.example.c3p0.springboot;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class C3p0SpringBootStarterApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
		System.err.println(jdbcTemplate.queryForObject("select now()", LocalDateTime.class));
	}

}
