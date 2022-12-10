package com.example.springschedule;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.GitHub;

public class GitHubTests {
	@Test
	public void testGitHub() {
		try {
			String accessToken = "";
			GitHub connect = GitHub.connectUsingOAuth(accessToken);
			System.err.println(connect.isCredentialValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
