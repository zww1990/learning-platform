package com.stampede.changepwd;

import org.junit.Test;
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
			String url = "http://gitlab.it.5i5j.com/users/auth/ldapmain/callback";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
			String username = "siqianwen";
			String password = "ainilaoma1234A";
			String csrfparam = "authenticity_token";
			String csrftoken = "qlPLbagm7IsysjTwkWS9jbCyNbSKAZFbjTzszsFIMtMXS9mpR+n7yvysCVrXAnopySPu9GYFn0Kj7z3m98M4jw==";
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
}
