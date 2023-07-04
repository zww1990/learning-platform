package com.runoob.design.chapter4.javaee.pattern31;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链
 */
public class FilterChain {
	private List<Filter> filters = new ArrayList<>();
	private Target target;

	public void addFilter(Filter filter) {
		filters.add(filter);
	}

	public void execute(String request) {
		this.filters.forEach(filter -> {
			filter.execute(request);
		});
		target.execute(request);
	}

	public void setTarget(Target target) {
		this.target = target;
	}
}
