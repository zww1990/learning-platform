package org.mybatis.generator.internal;

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
public class MyDefaultCommentGenerator extends DefaultCommentGenerator {
	@Override
	public void addComment(XmlElement xmlElement) {
		// add no comments by default
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
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
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
			sb.append(remarks); // $NON-NLS-1$
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

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		// add no comments by default
	}
}
