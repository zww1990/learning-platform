package org.mybatis.generator.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.ExampleFields;
import org.mybatis.generator.config.MethodComments;
import org.mybatis.generator.config.MyPropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * @author ZhangWeiWei
 * @date 2018年4月4日,上午9:29:29
 * @description 自定义mybatis文档注释生成器
 */
public class MyDefaultCommentGenerator extends DefaultCommentGenerator {
	private String author;
	private boolean addSwaggerAnnotations;
	private FullyQualifiedJavaType apiModelClass;
	private FullyQualifiedJavaType apiModelPropertyClass;
	private List<String> fields = new ArrayList<>();

	@Override
	public void addComment(XmlElement xmlElement) {
		// add no comments by default
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		if (compilationUnit.isJavaInterface()) {
			Interface topLevelClass = (Interface) compilationUnit;
			topLevelClass.addJavaDocLine("/**");
			topLevelClass.addJavaDocLine(new StringBuilder(" * @author ").append(this.author).toString());
			topLevelClass.addJavaDocLine(
					new StringBuilder(" * @description ").append("Data Access Object for domain model").toString());
			this.addJavadocTag(topLevelClass, false);
			topLevelClass.addJavaDocLine(" */");
		}
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
		method.addJavaDocLine("/**");
		method.addJavaDocLine(new StringBuilder(" * @author ").append(this.author).toString());
		StringBuilder sb1 = new StringBuilder(" * @description ");
		if (comment != null) {
			sb1.append(comment.getComment());
		}
		method.addJavaDocLine(sb1.toString());
		List<Parameter> params = method.getParameters();
		for (int i = 0, size = params.size(); i < size; i++) {
			StringBuilder sb2 = new StringBuilder(" * @param ").append(params.get(i).getName()).append(" ");
			if (comment != null) {
				sb2.append(comment.getRemark());
			}
			method.addJavaDocLine(sb2.toString());
		}
		this.addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(new StringBuilder(" * @author ").append(this.author).toString());
		topLevelClass.addJavaDocLine(
				new StringBuilder(" * @description ").append(introspectedTable.getRemarks()).toString());
		this.addJavadocTag(topLevelClass, false);
		topLevelClass.addJavaDocLine(" */");
		if (this.addSwaggerAnnotations) {
			topLevelClass.addImportedType(this.apiModelClass);
			topLevelClass.addImportedType(this.apiModelPropertyClass);
			topLevelClass.addAnnotation(new StringBuilder("@ApiModel(description = \"")
					.append(introspectedTable.getRemarks()).append("\")").toString());
		}
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(new StringBuilder(" * @author ").append(this.author).toString());
		innerClass.addJavaDocLine(
				new StringBuilder(" * @description ").append(introspectedTable.getRemarks()).toString());
		this.addJavadocTag(innerClass, false);
		innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(new StringBuilder(" * @author ").append(this.author).toString());
		innerClass.addJavaDocLine(
				new StringBuilder(" * @description ").append(introspectedTable.getRemarks()).toString());
		this.addJavadocTag(innerClass, false);
		innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (!StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			return;
		}
		field.addJavaDocLine(
				new StringBuilder("/** ").append(introspectedColumn.getRemarks()).append(" */").toString());
		if (!field.isFinal() && !field.isStatic() && this.addSwaggerAnnotations) {
			if (this.fields.contains(field.getName())) {
				return;
			}
			this.fields.add(field.getName());
			field.addAnnotation(new StringBuilder("@ApiModelProperty(value = \"")
					.append(introspectedColumn.getRemarks()).append("\")").toString());
		}
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (ExampleFields.hasField(field.getName())) {
			return;
		}
		if (!StringUtility.stringHasValue(introspectedTable.getRemarks())) {
			return;
		}
		field.addJavaDocLine(new StringBuilder("/** ").append(introspectedTable.getRemarks()).append(" */").toString());
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (!StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			return;
		}
		method.addJavaDocLine("/**");
		method.addJavaDocLine(new StringBuilder(" * @return ").append(introspectedColumn.getRemarks()).toString());
		this.addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (!StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			return;
		}
		method.addJavaDocLine("/**");
		method.addJavaDocLine(new StringBuilder(" * @param ").append(method.getParameters().get(0).getName())
				.append(" ").append(introspectedColumn.getRemarks()).toString());
		this.addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	@Override
	public void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		javaElement.addJavaDocLine(new StringBuilder(" * @date ").append(super.getDateString()).toString());
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		super.addConfigurationProperties(properties);
		this.author = properties.getProperty(MyPropertyRegistry.COMMENT_GENERATOR_AUTHOR);
		this.addSwaggerAnnotations = Boolean
				.valueOf(properties.getProperty(MyPropertyRegistry.COMMENT_GENERATOR_SWAGGER_ANNOTATIONS));
		if (this.addSwaggerAnnotations) {
			this.apiModelClass = new FullyQualifiedJavaType("io.swagger.annotations.ApiModel");
			this.apiModelPropertyClass = new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty");
		}
	}
}
