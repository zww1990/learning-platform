package com.example.springreactive.controller;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.springreactive.model.ClientUser;

/**
 * UserControllerTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午5:17:49
 */
@SpringBootTest
@AutoConfigureWebTestClient
public class UserControllerTests {
	@Resource
	private WebTestClient webClient;

	@Test
	public void testGetClientUser() {
		try {
			System.err.println(this.webClient.get()//
					.uri("/user/get")//
					.exchange()//
					.expectStatus()//
					.isOk()//
					.expectBody(String.class)//
					.returnResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddClientUser() {
		try {
			ClientUser user = new ClientUser()//
					.setGender(1)//
					.setPhoneNumber("1346579")//
					.setUserId("1004")//
					.setUsername("张三");
			System.err.println(this.webClient.post()//
					.uri("/user/add")//
					.contentType(MediaType.APPLICATION_JSON)//
					.bodyValue(user)//
					.exchange()//
					.expectStatus()//
					.isOk()//
					.expectBody(String.class)//
					.returnResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelClientUser() {
		try {
			String userId = "1001";
			System.err.println(this.webClient.delete()//
					.uri("/user/del/{userId}", userId)//
					.exchange()//
					.expectStatus()//
					.isOk()//
					.expectBody(String.class)//
					.returnResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
