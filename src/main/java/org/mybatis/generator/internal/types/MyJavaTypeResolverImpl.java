package org.mybatis.generator.internal.types;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

/**
 * 自定义mybatis类型解析器
 * @author ZhangWeiWei
 * @date 2018年3月2日,下午4:55:33
 * @description
 */
public class MyJavaTypeResolverImpl extends JavaTypeResolverDefaultImpl {
	@Override
	protected FullyQualifiedJavaType calculateBigDecimalReplacement(IntrospectedColumn column,
			FullyQualifiedJavaType defaultType) {
		if (forceBigDecimals) {
			return defaultType;
		} else {
			if (column.getScale() > 0 && column.getLength() > 0) {
				return new FullyQualifiedJavaType(Double.class.getName());
			} else if (column.getLength() > 9) {
				return new FullyQualifiedJavaType(Long.class.getName());
			} else if (column.getLength() > 4) {
				return new FullyQualifiedJavaType(Integer.class.getName());
			} else {
				return new FullyQualifiedJavaType(Integer.class.getName());
			}
		}
	}
}
