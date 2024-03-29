package com.runoob.design.chapter2.structural.pattern09;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工
 */
public class Employee {
	private String name;
	private String dept;
	private int salary;
	private List<Employee> subordinates;

	// 构造函数
	public Employee(String name, String dept, int sal) {
		this.name = name;
		this.dept = dept;
		this.salary = sal;
		subordinates = new ArrayList<>();
	}

	public void add(Employee e) {
		subordinates.add(e);
	}

	public void remove(Employee e) {
		subordinates.remove(e);
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public String toString() {
		return "Employee :[ Name : " + name + ", dept : " + dept
				+ ", salary :" + salary + " ]";
	}
}
