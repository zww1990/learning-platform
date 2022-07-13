package com.example.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * SimpleTests
 * @author zww19
 * @since 2022年5月31日,上午11:38:56
 */
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

	@Test
	public void testLocalDate() {
		System.err.println(LocalDate.now());
		System.err.println(LocalDate.now().minusMonths(1));
		System.err.println(LocalDate.now().withDayOfMonth(1));
		System.err.println(String.format(
				"SELECT id,staff_no,clock_time,longitude,latitude,address,clock_type,create_time FROM app_staff_clock_log WHERE staff_no='%s' and create_time between '%s 00:00:00' and '%s 23:59:59' ORDER BY create_time DESC",
				100230, LocalDate.now().minusMonths(1), LocalDate.now()));
	}
}
