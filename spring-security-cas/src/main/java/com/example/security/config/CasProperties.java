package com.example.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * CAS属性配置类
 * 
 * @author home
 */
@ConfigurationProperties(prefix = "cas")
public class CasProperties {
	/** CAS服务端配置 */
	@NestedConfigurationProperty
	private Server server = new Server();
	/** CAS客户端配置 */
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

	/**
	 * CAS服务端配置类
	 * 
	 * @author home
	 */
	public static class Server {
		/** 服务端URL前缀 */
		private String prefix;
		/** 服务端登录URL */
		private String login;
		/** 服务端注销URL */
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

	/**
	 * CAS客户端配置类
	 * 
	 * @author home
	 */
	public static class Client {
		/** 客户端URL前缀 */
		private String prefix;
		/** 客户端登录URL */
		private String login;
		/** 客户端注销URL */
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
