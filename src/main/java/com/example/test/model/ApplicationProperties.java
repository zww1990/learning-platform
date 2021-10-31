package com.example.test.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
public class ApplicationProperties {
	private String userLoginUrl;
	private String staffClockUrl;
	private String initStaffClockUrl;
	private String biSqlExecUrl;
	private Integer biSqlSourceId;
	private String selectAppStaffClockLogSql;
	private List<Address> addresses = new ArrayList<>();
	private List<UserInfo> users = new ArrayList<>();

	@Getter
	@Setter
	@ToString
	public static class Address {
		private Integer id;
		private String address;
		private BigDecimal longitude;
		private BigDecimal latitude;
	}

	@Getter
	@Setter
	@ToString
	public static class UserInfo {
		private String userNo;
		private String password;
		private String username;
	}
}
