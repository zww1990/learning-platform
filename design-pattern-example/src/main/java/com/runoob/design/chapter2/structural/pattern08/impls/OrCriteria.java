package com.runoob.design.chapter2.structural.pattern08.impls;

import com.runoob.design.chapter2.structural.pattern08.Criteria;
import com.runoob.design.chapter2.structural.pattern08.Person;

import java.util.List;

public class OrCriteria implements Criteria {
	private Criteria criteria;
	private Criteria otherCriteria;

	public OrCriteria(Criteria criteria, Criteria otherCriteria) {
		this.criteria = criteria;
		this.otherCriteria = otherCriteria;
	}

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
		List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persons);
		otherCriteriaItems.forEach(person -> {
			if (!firstCriteriaItems.contains(person)) {
				firstCriteriaItems.add(person);
			}
		});
		return firstCriteriaItems;
	}

}
