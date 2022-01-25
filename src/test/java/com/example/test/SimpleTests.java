package com.example.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SimpleTests {
	@Test
	public void testNoneMatch() {
		try {
			List<String> list = Arrays.asList("a", "b", "c", "d");
			System.err.println(list.stream().noneMatch(p -> p.equals("zzz")));
			System.err.println(list.stream().noneMatch(p -> p.equals("a")));
			System.err.println(list.stream().noneMatch(p -> p.equals("b")));
			System.err.println(list.stream().noneMatch(p -> p.equals("c")));
			System.err.println(list.stream().noneMatch(p -> p.equals("d")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
