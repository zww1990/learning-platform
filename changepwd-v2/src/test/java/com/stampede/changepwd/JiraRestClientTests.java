package com.stampede.changepwd;

import java.net.URI;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.atlassian.httpclient.api.Response;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousHttpClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;

public class JiraRestClientTests {
	@Test
	public void testJiraRestClient() {
		String url = "http://jira.bacic5i5j.com/";
		String username = "siqianwen";
		String password = "ainilaoma1234A";
		try (JiraRestClient client = new AsynchronousJiraRestClientFactory()
				.createWithBasicHttpAuthentication(URI.create(url), username, password)) {
			System.err.println(client.getUserClient().getUser(username).get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAsynchronousHttpClient() {
		DisposableHttpClient client = null;
		try {
			String url = "http://jira.bacic5i5j.com/";
			String username = "siqianwen";
			String password = "ainilaoma1234A";
			String syncUrl = "http://jira.bacic5i5j.com/rest/crowd/1/directory/10000";
			client = new AsynchronousHttpClientFactory().createClient(URI.create(url),
					new BasicHttpAuthenticationHandler(username, password));
			Response response = client.newRequest(syncUrl).get().get();
			System.err.println(response.getContentCharset());
			System.err.println(response.getContentType());
			System.err.println(response.getEntity());
			System.err.println(response.getStatusCode());
			System.err.println(response.getStatusText());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Optional.ofNullable(client).ifPresent(c -> {
				try {
					c.destroy();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}
}
