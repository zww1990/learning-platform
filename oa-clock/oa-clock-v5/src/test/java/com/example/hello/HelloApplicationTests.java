package com.example.hello;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * HelloApplicationTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年11月7日,上午10:15:01
 */
@SpringBootTest
public class HelloApplicationTests {
	@Resource
	private HelloService helloService;

	@Test
	public void contextLoads() {
		try {
			this.helloService.helloJob();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
