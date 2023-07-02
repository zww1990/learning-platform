package com.runoob.design.chapter2.structural.pattern8;

import com.runoob.design.chapter2.structural.pattern8.impls.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式（Filter Pattern）
 */
public class CriteriaPatternDemo {
	/**
	 * 使用不同的标准（Criteria）和它们的结合来过滤 Person 对象的列表。
	 */
	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();

		persons.add(new Person("Robert", "Male", "Single"));
		persons.add(new Person("John", "Male", "Married"));
		persons.add(new Person("Laura", "Female", "Married"));
		persons.add(new Person("Diana", "Female", "Single"));
		persons.add(new Person("Mike", "Male", "Single"));
		persons.add(new Person("Bobby", "Male", "Single"));

		Criteria male = new CriteriaMale();
		Criteria female = new CriteriaFemale();
		Criteria single = new CriteriaSingle();
		Criteria singleMale = new AndCriteria(single, male);
		Criteria singleOrFemale = new OrCriteria(single, female);

		System.out.println("Males: ");
		printPersons(male.meetCriteria(persons));

		System.out.println("\nFemales: ");
		printPersons(female.meetCriteria(persons));

		System.out.println("\nSingle Males: ");
		printPersons(singleMale.meetCriteria(persons));

		System.out.println("\nSingle Or Females: ");
		printPersons(singleOrFemale.meetCriteria(persons));
	}

	public static void printPersons(List<Person> persons) {
		persons.forEach(
				person -> {
					System.out.println("Person : [ Name : " + person.getName()
							+ ", Gender : " + person.getGender()
							+ ", Marital Status : " + person.getMaritalStatus()
							+ " ]");
				});
	}
}
