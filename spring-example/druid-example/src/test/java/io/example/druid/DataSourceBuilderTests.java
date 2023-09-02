package io.example.druid;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

public class DataSourceBuilderTests {
	@Test
	public void testCreate() {
		try {
			DataSource dataSource = DataSourceBuilder.create()//
					.type(DruidDataSource.class)//
					.url("jdbc:mysql://localhost:3306/example")//
					.username("root")//
					.password("root")//
					.build();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			System.err.println(jdbcTemplate.queryForList("select * from table"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
