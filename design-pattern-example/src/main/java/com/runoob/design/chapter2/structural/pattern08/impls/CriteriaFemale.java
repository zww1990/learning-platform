package com.runoob.design.chapter2.structural.pattern08.impls;

import com.runoob.design.chapter2.structural.pattern08.Criteria;
import com.runoob.design.chapter2.structural.pattern08.Person;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriaFemale implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		return persons.stream().filter(p -> p.getGender().equalsIgnoreCase("FEMALE")).collect(Collectors.toList());
	}

}
