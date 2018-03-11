package com.wiwj.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class StringTest {

	@Test
	public void testString() {
		try {
			String content = "[1][11][111][1111][11111]";
			System.err.println(content);
			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(content);
			List<Integer> numbers = new ArrayList<>();
			while (matcher.find()) {
				numbers.add(Integer.valueOf(matcher.group()));
			}
			System.err.println(numbers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
