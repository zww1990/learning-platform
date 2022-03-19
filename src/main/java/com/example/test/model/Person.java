package com.example.test.model;

import java.time.LocalDate;

public class Person {
	private Integer id;
	private String name;
	private int age;
	private LocalDate birthday;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public Person setId(Integer id) {
		this.id = id;
		return this;
	}

	public Person setName(String name) {
		this.name = name;
		return this;
	}

	public Person setAge(int age) {
		this.age = age;
		return this;
	}

	public Person setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	@Override
	public String toString() {
		return String.format("Person [id=%s, name=%s, age=%s, birthday=%s]", id, name, age, birthday);
	}
}
