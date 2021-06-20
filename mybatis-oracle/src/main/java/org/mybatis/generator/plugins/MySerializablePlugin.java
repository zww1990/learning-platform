package org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 自定义mybatis序列化插件
 * @author ZhangWeiWei
 * @date 2018年3月2日,下午3:53:09
 * @description
 */
public class MySerializablePlugin extends SerializablePlugin {
	private FullyQualifiedJavaType serializable;

	public MySerializablePlugin() {
		super();
		serializable = new FullyQualifiedJavaType("java.io.Serializable");
	}

	@Override
	protected void makeSerializable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		Boolean suppressJavaInterface = Boolean.valueOf(properties.getProperty("suppressJavaInterface"));
		if (!suppressJavaInterface) {
			topLevelClass.addImportedType(serializable);
			topLevelClass.addSuperInterface(serializable);
			topLevelClass.addAnnotation("@SuppressWarnings(\"serial\")");
		}
	}
}
