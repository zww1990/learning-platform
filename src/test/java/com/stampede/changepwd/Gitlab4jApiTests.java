package com.stampede.changepwd;

import org.gitlab4j.api.GitLabApi;
import org.junit.Test;

public class Gitlab4jApiTests {
	@Test
	public void testGitlab4jApi() {
		String url = "http://gitlab.it.5i5j.com/";
		String username = "siqianwen";
		String password = "ainilaoma1234A";
		try (GitLabApi api = GitLabApi.oauth2Login(url, username, password)) {
			System.err.println(api.getAuthToken());
			System.err.println(api.getGitLabServerUrl());
			System.err.println(api.getSecretToken());
			System.err.println(api.getApiVersion());
			System.err.println(api.getUserApi().getCurrentUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
