package org.mybatis.generator.internal;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
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
@SuppressWarnings("serial")
public class MyDefaultCommentGenerator extends DefaultCommentGenerator {
	private static final Map<String, String> methodComments = new HashMap<String, String>() {
		{
			put("deleteByPrimaryKey", "按主键删除记录");
			put("insert", "插入记录");
			put("insertSelective", "选择性插入记录");
			put("selectByPrimaryKey", "按主键查询记录");
			put("updateByPrimaryKeySelective", "按主键选择性更新记录");
			put("updateByPrimaryKeyWithBLOBs", "按主键更新记录包含所有BLOB类型的字段");
			put("updateByPrimaryKey", "按主键更新记录排除所有BLOB类型的字段");
		}
	};

	@Override
	public void addComment(XmlElement xmlElement) {
		// add no comments by default
	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		String methodName = method.getName();
		if (methodComments.containsKey(methodName)) {
			StringBuilder sb = new StringBuilder();
			method.addJavaDocLine("/**"); //$NON-NLS-1$
			method.addJavaDocLine(" * @author ZhangWeiWei"); //$NON-NLS-1$
			sb.append(" * @description "); //$NON-NLS-1$
			sb.append(methodComments.get(methodName)); // $NON-NLS-1$
			method.addJavaDocLine(sb.toString());
			addJavadocTag(method, false);
			method.addJavaDocLine(" */"); //$NON-NLS-1$
		}
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$
		topLevelClass.addJavaDocLine(" * @author ZhangWeiWei"); //$NON-NLS-1$
		String remarks = introspectedTable.getRemarks();
		if (StringUtility.stringHasValue(remarks)) {
			StringBuilder sb = new StringBuilder();
			sb.append(" * @description ");
			sb.append(remarks);
			topLevelClass.addJavaDocLine(sb.toString()); // $NON-NLS-1$
		}
		addJavadocTag(topLevelClass, true);
		topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	@Override
	public void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		String s = getDateString();
		if (s != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(" * @date ");
			sb.append(s);
			javaElement.addJavaDocLine(sb.toString());
		}
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		if (StringUtility.stringHasValue(remarks)) {
			StringBuilder sb = new StringBuilder();
			sb.append("/*** "); //$NON-NLS-1$
			sb.append(trimAllWhitespace(remarks)); // $NON-NLS-1$
			sb.append(" */"); //$NON-NLS-1$
			field.addJavaDocLine(sb.toString()); // $NON-NLS-1$
		}
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		if (StringUtility.stringHasValue(remarks)) {
			StringBuilder sb = new StringBuilder();
			method.addJavaDocLine("/**"); //$NON-NLS-1$
			sb.append(" * @return "); //$NON-NLS-1$
			sb.append(remarks); // $NON-NLS-1$
			method.addJavaDocLine(sb.toString());
			addJavadocTag(method, false);
			method.addJavaDocLine(" */"); //$NON-NLS-1$
		}
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		if (StringUtility.stringHasValue(remarks)) {
			StringBuilder sb = new StringBuilder();
			method.addJavaDocLine("/**"); //$NON-NLS-1$
			Parameter parm = method.getParameters().get(0);
			sb.append(" * @param "); //$NON-NLS-1$
			sb.append(parm.getName());
			sb.append(" "); //$NON-NLS-1$
			sb.append(remarks);
			method.addJavaDocLine(sb.toString());
			addJavadocTag(method, false);
			method.addJavaDocLine(" */"); //$NON-NLS-1$
		}
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
