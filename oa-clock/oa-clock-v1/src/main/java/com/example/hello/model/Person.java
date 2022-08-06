package com.example.hello.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {
	private Integer id;
	private String name;
	private int age;
	private LocalDate birthday;
}
