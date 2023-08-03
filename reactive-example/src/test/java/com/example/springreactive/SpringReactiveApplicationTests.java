package com.example.springreactive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * SpringReactiveApplicationTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午5:53:42
 */
@SpringBootTest
@AutoConfigureWebTestClient
public class SpringReactiveApplicationTests {
	@Autowired
	private WebTestClient webClient;
	@Autowired
	private R2dbcEntityTemplate entityTemplate;

	@Test
	public void testContextLoads() {
		System.err.println(this.webClient);
		System.err.println(this.entityTemplate);
	}

}
