package com.stampede.changepwd;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTests {
	@Test
	public void testSignIn() {
		try {
			RestTemplate template = new RestTemplate();
			String url = "";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
			String username = "";
			String password = "";
			String csrfparam = "";
			String csrftoken = "";
			data.add("username", username);
			data.add("password", password);
			data.add(csrfparam, csrftoken);
			HttpEntity<Object> request = new HttpEntity<Object>(data, headers);
			ResponseEntity<String> post = template.postForEntity(url, request, String.class);
			System.err.println(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLogin() {
		try {
			RestTemplate template = new RestTemplate();
			String url = "";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
			String username = "";
			String password = "";
			String csrfparam = "";
			String csrftoken = "";
			data.add("os_username", username);
			data.add("os_password", password);
			data.add(csrfparam, csrftoken);
			HttpEntity<Object> request = new HttpEntity<Object>(data, headers);
			ResponseEntity<String> post = template.postForEntity(url, request, String.class);
			System.err.println(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
