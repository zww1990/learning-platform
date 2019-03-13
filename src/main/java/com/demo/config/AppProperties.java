package com.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class AppProperties {
	@Configuration
	@PropertySource("classpath:application.properties")
	public static class JdbcProperties {
		@Value("${jdbc.driver.class}")
		private String driverClass;
		@Value("${jdbc.url}")
		private String url;
		@Value("${jdbc.user}")
		private String user;
		@Value("${jdbc.password}")
		private String password;

		public String getDriverClass() {
			return driverClass;
		}

		public void setDriverClass(String driverClass) {
			this.driverClass = driverClass;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
