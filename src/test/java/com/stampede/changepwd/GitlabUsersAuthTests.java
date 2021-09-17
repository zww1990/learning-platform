package com.stampede.changepwd;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GitlabUsersAuthTests {
	@Test
	public void testLogin() {
		try {
			Connection connect = Jsoup.connect("http://gitlab.it.5i5j.com/users/sign_in");
			Response response = connect.execute();
			Map<String, String> cookies = response.cookies();
			cookies.put("diff_view", "inline");
			cookies.put("sidebar_collapsed", "false");
			cookies.put("event_filter", "all");
//			System.err.println(cookies);
			Element head = response.parse().head();
			String csrfparam = head.select("meta[name=csrf-param]").attr("content");
			String csrftoken = head.select("meta[name=csrf-token]").attr("content");
//			System.err.println(csrfparam);
//			System.err.println(csrftoken);
			String username = "siqianwen";
			String password = "ainilaoma1234A";
			Map<String, String> data = new HashMap<>();
			data.put("username", username);
			data.put("password", password);
			data.put(csrfparam, csrftoken);
			Map<String, String> headers = new HashMap<>();
			headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			headers.put("Host", "gitlab.it.5i5j.com");
			headers.put("Accept", "*/*");
			headers.put("Accept-Encoding", "gzip, deflate");
			headers.put("Accept-Language", "zh-CN,zh;q=0.9");
			headers.put("Referer", "http://gitlab.it.5i5j.com/");
			Document post = Jsoup.connect("http://gitlab.it.5i5j.com/users/auth/ldapmain/callback")//
					.headers(headers)//
					.data(data)//
					.cookies(cookies)//
					.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")
					.post();
			System.err.println(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
