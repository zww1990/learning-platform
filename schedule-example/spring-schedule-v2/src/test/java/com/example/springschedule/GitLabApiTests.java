package com.example.springschedule;

import org.gitlab4j.api.GitLabApi;
import org.junit.jupiter.api.Test;

public class GitLabApiTests {
	@Test
	public void testGitLabApi() {
		try {
			String url = "";
			String username = "";
			String password = "";
			GitLabApi oauth2Login = GitLabApi.oauth2Login(url, username, password);
			System.err.println(oauth2Login);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
