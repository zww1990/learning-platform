package io.example.druid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootTest
public class DruidExampleApplicationTests {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testContextLoads() {
		System.err.println(this.applicationContext.getBean(DataSource.class));
		System.err.println(this.applicationContext.getBean(JdbcTemplate.class));
	}

}
