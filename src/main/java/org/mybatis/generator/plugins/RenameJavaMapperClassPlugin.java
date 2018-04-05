package org.mybatis.generator.plugins;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

public class RenameJavaMapperClassPlugin extends PluginAdapter {
	private String searchString;
	private String replaceString;
	private Pattern pattern;

	@Override
	public boolean validate(List<String> warnings) {
		searchString = properties.getProperty("searchString");
		replaceString = properties.getProperty("replaceString");
		boolean valid = stringHasValue(searchString) && stringHasValue(replaceString);
		if (valid) {
			pattern = Pattern.compile(searchString);
		} else {
			if (!stringHasValue(searchString)) {
				warnings.add(getString("ValidationError.18", "RenameJavaMapperClassPlugin", "searchString"));
			}
			if (!stringHasValue(replaceString)) {
				warnings.add(getString("ValidationError.18", "RenameJavaMapperClassPlugin", "replaceString"));
			}
		}
		return valid;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		String oldType = introspectedTable.getMyBatis3JavaMapperType();
		Matcher matcher = pattern.matcher(oldType);
		oldType = matcher.replaceAll(replaceString);
		introspectedTable.setMyBatis3JavaMapperType(oldType);
	}
}
