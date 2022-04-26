package com.example.springreactive;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * SpringReactiveApplicationTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午5:53:42
 */
@SuppressWarnings("deprecation")
@SpringBootTest
@AutoConfigureWebTestClient
public class SpringReactiveApplicationTests {
	@Resource
	private DatabaseClient databaseClient;
	@Resource
	private WebTestClient webClient;

	@Test
	public void contextLoads() {
		System.err.println(this.databaseClient);
		System.err.println(this.webClient);
	}

}
