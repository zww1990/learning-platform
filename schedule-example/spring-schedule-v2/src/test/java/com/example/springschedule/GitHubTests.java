package com.example.springschedule;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.GitHub;

public class GitHubTests {
	@Test
	public void testGitHub() {
		try {
			String login = "";
			String token = "";
			GitHub connect = GitHub.connect(login, token);
			System.err.println(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
