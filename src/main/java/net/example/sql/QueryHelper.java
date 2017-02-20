package net.example.sql;

import static java.util.regex.Pattern.compile;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryHelper {
	private static Pattern CONDITION = compile("#\\{(.+?)\\}");
	private String queryString;
	private List<QueryPart> queryParts = new CopyOnWriteArrayList<>();
	private Map<String, Object> queryParams = new ConcurrentHashMap<String, Object>();

	public QueryHelper(String queryString, Map<String, Object> params) {
		int index = 0;
		Matcher matcher = CONDITION.matcher(queryString);
		while (matcher.find()) {
			if (matcher.start() != index) {
				String s = queryString.substring(index, matcher.start());
				if (hasText(s)) {
					QueryPart e = new QueryPart(false, s);
					queryParts.add(e);
				}
			}
			QueryPart e = new QueryPart(true, matcher.group(1));
			queryParts.add(e);
			index = matcher.end();
		}
		if (index != queryString.length()) {
			QueryPart e = new QueryPart(false, queryString.substring(index));
			queryParts.add(e);
		}
		StringBuilder sb = new StringBuilder();
		this.queryParts.stream().forEach(qp -> {
			if (qp.isQueryString()) {
				if (params.containsKey(qp.getParamName())) {
					Object value = params.get(qp.getParamName());
					if (value instanceof String) {
						if (hasText((String) value)) {
							sb.append(qp.getQueryString());
							queryParams.put(qp.getParamName(), value);
						}
					} else if (value instanceof Collection) {
						if (!((Collection<?>) value).isEmpty()) {
							sb.append(qp.getQueryString());
							queryParams.put(qp.getParamName(), value);
						}
					} else if (value instanceof Object[]) {
						if (((Object[]) value).length > 0) {
							sb.append(qp.getQueryString());
							queryParams.put(qp.getParamName(), value);
						}
					} else {
						if (value != null) {
							sb.append(qp.getQueryString());
							queryParams.put(qp.getParamName(), value);
						}
					}
				}
			} else {
				sb.append(qp.getQueryString());
			}
		});
		this.queryString = sb.toString();
	}

	public String getQueryString() {
		return queryString;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
}
