package com.example.security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SimpleTests {
	@Test
	public void testPasswordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.err.println(encoder.encode("admin"));
		System.err.println(encoder.encode("guest1"));
		System.err.println(encoder.encode("guest2"));
		System.err.println(encoder.encode("guest3"));
		System.err.println(encoder.encode("guest4"));
	}
}
