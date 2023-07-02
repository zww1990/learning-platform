package com.runoob.design.chapter2.structural.pattern8.impls;

import com.runoob.design.chapter2.structural.pattern8.Criteria;
import com.runoob.design.chapter2.structural.pattern8.Person;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriaMale implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		return persons.stream().filter(p -> p.getGender().equalsIgnoreCase("MALE")).collect(Collectors.toList());
	}

}
