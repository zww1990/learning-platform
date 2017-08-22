package net.other.sql;

import java.util.HashMap;
import java.util.Map;

public class QueryTest {

	public static void main(String[] args) {
		String queryString = "SELECT DATE_FORMAT(t.access_time, '%Y-%m-%d') d, count(*) FROM pub_sys_log t where 1=1 #{ and DATE_FORMAT(t.access_time, '%Y-%m-%d')>=:begin} #{ and DATE_FORMAT(t.access_time, '%Y-%m-%d')<=:end} GROUP BY d ORDER BY d";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("begin", "aa");
		params.put("end", "bb");
		QueryHelper helper = new QueryHelper(queryString, params);
		System.out.println(helper.getQueryString());
		System.out.println(helper.getQueryParams());
	}
}
