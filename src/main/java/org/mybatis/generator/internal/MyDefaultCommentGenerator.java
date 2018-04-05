package org.mybatis.generator.internal;

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

/**
 * @author ZhangWeiWei
 * @date 2018年4月4日,上午9:29:29
 * @description 自定义mybatis文档注释生成器
 */
public class MyDefaultCommentGenerator extends DefaultCommentGenerator {
	enum MethodComments {
		/*** 按主键删除记录 */
		deleteByPrimaryKey("按主键删除记录", true),
		/*** 插入记录 */
		insert("插入记录", false),
		/*** 选择性插入记录 */
		insertSelective("选择性插入记录", false),
		/*** 按主键查询记录 */
		selectByPrimaryKey("按主键查询记录", true),
		/*** 按主键选择性更新记录 */
		updateByPrimaryKeySelective("按主键选择性更新记录", false),
		/*** 按主键更新记录包含所有BLOB类型的字段 */
		updateByPrimaryKeyWithBLOBs("按主键更新记录包含所有BLOB类型的字段", false),
		/*** 按主键更新记录排除所有BLOB类型的字段 */
		updateByPrimaryKey("按主键更新记录排除所有BLOB类型的字段", false);
		private String comment;
		private boolean isPk;

		private MethodComments(String comment, boolean isPk) {
			this.comment = comment;
			this.isPk = isPk;
		}

	}

	@Override
	public void addComment(XmlElement xmlElement) {
		// add no comments by default
	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
		// add no annotations by default
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
		// add no annotations by default
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
		// add no annotations by default
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
		// add no annotations by default
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
		// add no annotations by default
	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		MethodComments comment = MethodComments.valueOf(method.getName());
		if (comment != null) {
			StringBuilder sb1 = new StringBuilder();
			method.addJavaDocLine("/**"); //$NON-NLS-1$
			method.addJavaDocLine(" * @author ZhangWeiWei"); //$NON-NLS-1$
			sb1.append(" * @description "); //$NON-NLS-1$
			sb1.append(comment.comment); // $NON-NLS-1$
			method.addJavaDocLine(sb1.toString());
			StringBuilder sb2 = new StringBuilder();
			Parameter parm = method.getParameters().get(0);
			sb2.append(" * @param "); //$NON-NLS-1$
			sb2.append(parm.getName());
			sb2.append(" "); //$NON-NLS-1$
			sb2.append(comment.isPk ? "主键" : introspectedTable.getRemarks());
			method.addJavaDocLine(sb2.toString());
			this.addJavadocTag(method);
			method.addJavaDocLine(" */"); //$NON-NLS-1$
		}
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$
		topLevelClass.addJavaDocLine(" * @author ZhangWeiWei"); //$NON-NLS-1$
		String remarks = introspectedTable.getRemarks();
		StringBuilder sb = new StringBuilder();
		sb.append(" * @description ");
		sb.append(remarks);
		topLevelClass.addJavaDocLine(sb.toString()); // $NON-NLS-1$
		this.addJavadocTag(topLevelClass);
		topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		StringBuilder sb = new StringBuilder();
		sb.append("/*** "); //$NON-NLS-1$
		sb.append(trimAllWhitespace(remarks)); // $NON-NLS-1$
		sb.append(" */"); //$NON-NLS-1$
		field.addJavaDocLine(sb.toString()); // $NON-NLS-1$
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**"); //$NON-NLS-1$
		sb.append(" * @return "); //$NON-NLS-1$
		sb.append(remarks); // $NON-NLS-1$
		method.addJavaDocLine(sb.toString());
		this.addJavadocTag(method);
		method.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**"); //$NON-NLS-1$
		Parameter parm = method.getParameters().get(0);
		sb.append(" * @param "); //$NON-NLS-1$
		sb.append(parm.getName());
		sb.append(" "); //$NON-NLS-1$
		sb.append(remarks);
		method.addJavaDocLine(sb.toString());
		this.addJavadocTag(method);
		method.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	private void addJavadocTag(JavaElement javaElement) {
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
