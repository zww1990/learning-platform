package org.mybatis.generator.config;

import java.util.Arrays;

public enum ExampleFields {
	orderByClause, distinct, oredCriteria;

	public static boolean hasField(String name) {
		return Arrays.stream(values()).anyMatch(x -> x.name().equals(name));
	}
}
