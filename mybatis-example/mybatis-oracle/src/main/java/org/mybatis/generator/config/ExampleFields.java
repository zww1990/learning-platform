package org.mybatis.generator.config;

import java.util.Arrays;

/**
 * @author ZhangWeiWei
 * @date 2018年4月8日,上午9:45:34
 * @description
 */
public enum ExampleFields {
	orderByClause, distinct, oredCriteria;

	public static boolean hasField(String name) {
		return Arrays.stream(values()).anyMatch(x -> x.name().equals(name));
	}
}
