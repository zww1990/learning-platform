package org.mybatis.generator.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * @author ZhangWeiWei
 * @date 2018年4月4日,上午9:29:29
 * @description 自定义mybatis文档注释生成器
 */
public class MyDefaultCommentGenerator extends DefaultCommentGenerator {
	public enum MethodComments {
		deleteByPrimaryKey("按主键删除记录", true), insert("插入记录", false), insertSelective("选择性插入记录",
				false), selectByPrimaryKey("按主键查询记录", true), updateByPrimaryKeySelective("按主键选择性更新记录",
						false), updateByPrimaryKeyWithBLOBs("按主键更新记录包含所有BLOB类型的字段",
								false), updateByPrimaryKey("按主键更新记录", false);
		private String comment;
		private boolean isPk;

		private MethodComments(String comment, boolean isPk) {
			this.comment = comment;
			this.isPk = isPk;
		}

		public static MethodComments value(String name) {
			return Arrays.stream(values()).filter(x -> x.name().equals(name)).findFirst().orElse(null);
		}
	}

	public enum ExampleFields {
		orderByClause, distinct, oredCriteria;
		public static boolean hasField(String name) {
			return Arrays.stream(values()).anyMatch(x -> x.name().equals(name));
		}
	}

	@Override
	public void addComment(XmlElement xmlElement) {
		// add no comments by default
	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
		this.addClassComment(innerClass, introspectedTable);
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
		this.addFieldComment(field, introspectedTable, introspectedColumn);
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
		this.addFieldComment(field, introspectedTable);
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
		if (method.getName().startsWith("get")) {
			this.addGetterComment(method, introspectedTable, introspectedColumn);
		} else if (method.getName().startsWith("set")) {
			this.addSetterComment(method, introspectedTable, introspectedColumn);
		}
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
		this.addGeneralMethodComment(method, introspectedTable);
	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		MethodComments comment = MethodComments.value(method.getName());
		StringBuilder sb1 = new StringBuilder();
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * @author ZhangWeiWei");
		sb1.append(" * @description ");
		if (comment != null) {
			sb1.append(comment.comment);
		}
		method.addJavaDocLine(sb1.toString());
		List<Parameter> params = method.getParameters();
		if (!params.isEmpty()) {
			StringBuilder sb2 = new StringBuilder();
			sb2.append(" * @param ");
			sb2.append(params.get(0).getName());
			sb2.append(" ");
			if (comment != null) {
				sb2.append(comment.isPk ? "主键" : introspectedTable.getRemarks());
			}
			method.addJavaDocLine(sb2.toString());
		}
		this.addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * @author ZhangWeiWei");
		String remarks = introspectedTable.getRemarks();
		StringBuilder sb = new StringBuilder();
		sb.append(" * @description ");
		sb.append(remarks);
		topLevelClass.addJavaDocLine(sb.toString());
		this.addJavadocTag(topLevelClass, false);
		topLevelClass.addJavaDocLine(" */");
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(" * @author ZhangWeiWei");
		String remarks = introspectedTable.getRemarks();
		StringBuilder sb = new StringBuilder();
		sb.append(" * @description ");
		sb.append(remarks);
		innerClass.addJavaDocLine(sb.toString());
		this.addJavadocTag(innerClass, false);
		innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(" * @author ZhangWeiWei");
		String remarks = introspectedTable.getRemarks();
		StringBuilder sb = new StringBuilder();
		sb.append(" * @description ");
		sb.append(remarks);
		innerClass.addJavaDocLine(sb.toString());
		this.addJavadocTag(innerClass, false);
		innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		if (!StringUtility.stringHasValue(remarks)) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("/*** ");
		sb.append(trimAllWhitespace(remarks));
		sb.append(" */");
		field.addJavaDocLine(sb.toString());
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (ExampleFields.hasField(field.getName())) {
			return;
		}
		String remarks = introspectedTable.getRemarks();
		if (!StringUtility.stringHasValue(remarks)) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("/*** ");
		sb.append(remarks);
		sb.append(" */");
		field.addJavaDocLine(sb.toString());
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**");
		sb.append(" * @return ");
		sb.append(remarks);
		method.addJavaDocLine(sb.toString());
		this.addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**");
		Parameter parm = method.getParameters().get(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		sb.append(" ");
		sb.append(remarks);
		method.addJavaDocLine(sb.toString());
		this.addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	@Override
	public void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		StringBuilder sb = new StringBuilder();
		sb.append(" * @date ");
		sb.append(super.getDateString());
		javaElement.addJavaDocLine(sb.toString());
	}

	private static String trimAllWhitespace(String str) {
		int len = str.length();
		StringBuilder sb = new StringBuilder(str.length());
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (!Character.isWhitespace(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
