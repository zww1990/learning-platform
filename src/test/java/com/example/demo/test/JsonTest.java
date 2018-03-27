package com.example.demo.test;

import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Base64Utils;

import com.example.demo.web.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
	@Test
	public void testJson() {
		try {
			UserModel model = new UserModel();
			model.setUserId(1324);
			model.setUserName("我是谁");
			ObjectMapper mapper = new ObjectMapper();
			String value = mapper.writeValueAsString(model);
			System.err.println(value);
			value = Base64Utils.encodeToString(value.getBytes());
			System.err.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testProperties() {
		Resource resource = new ClassPathResource("application.properties");
		try (InputStream in = resource.getInputStream()) {
			Properties props = new Properties();
			props.load(in);
			System.err.println(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
