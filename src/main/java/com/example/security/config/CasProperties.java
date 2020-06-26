package com.example.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "cas")
public class CasProperties {
	@NestedConfigurationProperty
	private Server server = new Server();
	@NestedConfigurationProperty
	private Client client = new Client();

	public Server getServer() {
		return server;
	}

	public Client getClient() {
		return client;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public static class Server {
		private String prefix;
		private String login;
		private String logout;

		public String getPrefix() {
			return prefix;
		}

		public String getLogin() {
			return login;
		}

		public String getLogout() {
			return logout;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public void setLogout(String logout) {
			this.logout = logout;
		}
	}

	public static class Client {
		private String prefix;
		private String login;
		private String logout;

		public String getPrefix() {
			return prefix;
		}

		public String getLogin() {
			return login;
		}

		public String getLogout() {
			return logout;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public void setLogout(String logout) {
			this.logout = logout;
		}
	}
}
