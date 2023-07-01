package com.runoob.design.chapter2.structural.filtercriteria;

import java.util.List;

public interface Criteria {
	public List<Person> meetCriteria(List<Person> persons);
}
