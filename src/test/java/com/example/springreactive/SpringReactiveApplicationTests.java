package com.example.springreactive;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;

/**
 * SpringReactiveApplicationTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午5:53:42
 */
@SuppressWarnings("deprecation")
@SpringBootTest
public class SpringReactiveApplicationTests {
	@Resource
	private DatabaseClient databaseClient;

	@Test
	public void contextLoads() {
		System.err.println(this.databaseClient);
	}

}
