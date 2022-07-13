package com.example.demo.test;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class DemoControllerTests {
	@Resource
	private TestRestTemplate rest;
	@Resource
	private ObjectMapper json;

	@Test
	public void testUpdateUsePut() {
		try {
			UserModel user = new UserModel();
			user.setUserId(22);
			user.setUserName("张三");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("token", "11");
			HttpEntity<?> entity = new HttpEntity<>(user, headers);
			rest.put("/demo/update", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateUseExchange() {
		try {
			UserModel user = new UserModel();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("token", "11");
			HttpEntity<?> entity = new HttpEntity<>(user, headers);
			ResponseEntity<Object> response = rest.exchange("/demo/update", HttpMethod.PUT, entity, Object.class);
			System.err.println(this.json.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
