package com.stampede.changepwd;

import java.net.URI;

import org.junit.Test;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

public class JiraRestClientTests {
	@Test
	public void testJiraRestClient() {
		String url = "http://jira.bacic5i5j.com/";
		String username = "siqianwen";
		String password = "ainilaoma1234A";
		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
		try (JiraRestClient client = factory.createWithBasicHttpAuthentication(URI.create(url), username, password)) {
			System.err.println(client.getUserClient().getUser(username).get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
