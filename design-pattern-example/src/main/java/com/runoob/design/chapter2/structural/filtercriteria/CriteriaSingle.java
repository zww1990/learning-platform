package com.runoob.design.chapter2.structural.filtercriteria;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSingle implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> singlePersons = new ArrayList<Person>();
		persons.stream().forEach(person -> {
			if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
				singlePersons.add(person);
			}
		});
		return singlePersons;
	}

}
