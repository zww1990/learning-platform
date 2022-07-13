package com.example.client.test;

import org.jasig.cas.client.authentication.RegexUrlPatternMatcherStrategy;
import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;
import org.junit.Test;

public class UrlPatternMatcherStrategyTests {
	@Test
	public void testRegexMatches() {
		try {
			String pattern = "/aaa*|/lib/*";
			UrlPatternMatcherStrategy strategy = new RegexUrlPatternMatcherStrategy(pattern);
			System.err.println(strategy.matches(""));
			System.err.println(strategy.matches("http://localhost:8081/client-a/"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/test111"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/test/aaa"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/test/aaa/bbb"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/test.html"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/aaaa/demo.html"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/aaaa/demo/bbbb"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/aaaa/demo222"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/lib/demo.html"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/lib/demo/dddd"));
			System.err.println(strategy.matches("http://localhost:8081/client-a/lib/demo4444"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
