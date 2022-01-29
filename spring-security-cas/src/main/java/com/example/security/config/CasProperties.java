package com.example.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * CAS属性配置类
 * 
 * @author home
 */
@ConfigurationProperties(prefix = "cas")
@Getter
@Setter
@ToString
public class CasProperties {
	/** CAS服务端配置 */
	@NestedConfigurationProperty
	private Server server = new Server();
	/** CAS客户端配置 */
	@NestedConfigurationProperty
	private Client client = new Client();

	/**
	 * CAS服务端配置类
	 * 
	 * @author home
	 */
	@Getter
	@Setter
	@ToString
	public static class Server {
		/** 服务端URL前缀 */
		private String prefix;
		/** 服务端登录URL */
		private String login;
		/** 服务端注销URL */
		private String logout;

	}

	/**
	 * CAS客户端配置类
	 * 
	 * @author home
	 */
	@Getter
	@Setter
	@ToString
	public static class Client {
		/** 客户端URL前缀 */
		private String prefix;
		/** 客户端登录URL */
		private String login;
		/** 客户端注销URL */
		private String logout;

	}
}
