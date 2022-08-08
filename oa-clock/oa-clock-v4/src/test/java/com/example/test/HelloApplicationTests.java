package com.example.test;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.example.hello.HelloApplication;

/**
 * HelloApplicationTests
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午4:25:31
 */
@SpringBootTest(classes = HelloApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloApplicationTests {
	@Resource
	private ApplicationContext context;

	@Test
	public void contextLoads() {
		try {
			System.err.println(this.context.getBean(ServerEndpointExporter.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
