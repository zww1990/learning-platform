package com.example.hibernate;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MainTest {
	public static void main(String[] args) {
		System.err.println(LocalDate.now() + "\t" + new Date(System.currentTimeMillis()));
		System.err.println(LocalTime.now() + "\t" + new Time(System.currentTimeMillis()));
		System.err.println(LocalDateTime.now() + "\t" + new Timestamp(System.currentTimeMillis()));
		System.err.println(LocalDate.class.getCanonicalName());
		System.err.println(LocalDate.class.getName());
		System.err.println(LocalDate.class.getTypeName());
	}
}
