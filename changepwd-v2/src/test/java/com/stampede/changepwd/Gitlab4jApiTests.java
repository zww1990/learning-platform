package com.stampede.changepwd;

import org.gitlab4j.api.GitLabApi;
import org.junit.jupiter.api.Test;

public class Gitlab4jApiTests {
	@Test
	public void testGitlab4jApi() {
		String url = "http://10.2.4.174/";
		String username = "wudi03";
		String password = "20210915";
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
