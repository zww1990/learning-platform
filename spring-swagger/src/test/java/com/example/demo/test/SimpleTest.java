package com.example.demo.test;

import java.util.TimeZone;

import org.junit.jupiter.api.Test;

public class SimpleTest {
	@Test
	public void testTimeZone() {
		System.err.println(TimeZone.getDefault());
	}
}
