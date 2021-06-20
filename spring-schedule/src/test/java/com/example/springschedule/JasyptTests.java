package com.example.springschedule;

import javax.annotation.Resource;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTests {
	@Resource
	private StringEncryptor encryptor;

	@Test
	public void encodePassword() {
		for (int i = 0; i < 10; i++) {
			System.err.println(this.encryptor.encrypt("root"));
		}
	}
}
