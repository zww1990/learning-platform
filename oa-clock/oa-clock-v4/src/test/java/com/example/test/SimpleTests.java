package com.example.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.quartz.CronScheduleBuilder;

/**
 * SimpleTests
 * 
 * @author zhang weiwei
 * @since 2022年5月31日,上午11:38:56
 */
public class SimpleTests {
	@Test
	public void testUUID() {
		try {
			System.err.println(UUID.randomUUID().toString().replace("-", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCronExpression() {
		try {
			System.err.println(CronScheduleBuilder.cronSchedule("0 0 9,19 ? * 1-5"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testThreadLocalRandom() {
		try {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			BigDecimal longitude = BigDecimal.valueOf(116);
			BigDecimal latitude = BigDecimal.valueOf(39);
			for (int i = 0; i < 100; i++) {
				System.err.println(
						BigDecimal.valueOf(random.nextFloat()).add(longitude).setScale(7, RoundingMode.HALF_UP));
				System.err.println(
						BigDecimal.valueOf(random.nextFloat()).add(latitude).setScale(7, RoundingMode.HALF_UP));
				System.err.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testThreadLocalRandom1() {
		try {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			LocalDate date = LocalDate.now();
			for (int i = 0; i < 100; i++) {
				System.err.println(
						LocalDateTime.of(date, LocalTime.of(8, 30 + random.nextInt(0, 30), random.nextInt(1, 60))));
				System.err.println(
						LocalDateTime.of(date, LocalTime.of(18, 30 + random.nextInt(0, 30), random.nextInt(1, 60))));
				System.err.println("----------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	@Test
	public void testLocalDateTime() {
		try {
			System.err.println(LocalDateTime.now() + "\t" + LocalDateTime.now().get(ChronoField.AMPM_OF_DAY));
			System.err.println(LocalTime.now() + "\t" + LocalTime.now().get(ChronoField.AMPM_OF_DAY));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
