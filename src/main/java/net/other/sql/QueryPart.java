package net.other.sql;

import static java.util.regex.Pattern.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryPart {
	private static Pattern PARAM = compile(":([\\w\\d]+(\\___[\\w\\d\\_]+)?(\\[.*\\])?)");
	private boolean isQueryString;
	private String queryString;
	private String paramName;

	public QueryPart(boolean isQueryString, String queryString) {
		this.isQueryString = isQueryString;
		this.queryString = queryString;
		Matcher matcher = PARAM.matcher(queryString);
		while (matcher.find()) {
			this.paramName = matcher.group(1);
		}
	}

	public boolean isQueryString() {
		return isQueryString;
	}

	public String getQueryString() {
		return queryString;
	}

	public String getParamName() {
		return paramName;
	}

}
