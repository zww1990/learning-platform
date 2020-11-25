package com.runoob.design.filter;

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
		otherCriteriaItems.stream().forEach(person -> {
			if (!firstCriteriaItems.contains(person)) {
				firstCriteriaItems.add(person);
			}
		});
		return firstCriteriaItems;
	}

}
