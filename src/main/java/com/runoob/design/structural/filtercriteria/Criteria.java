package com.runoob.design.structural.filtercriteria;

import java.util.List;

public interface Criteria {
	public List<Person> meetCriteria(List<Person> persons);
}
