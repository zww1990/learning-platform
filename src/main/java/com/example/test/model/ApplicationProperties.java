package com.example.test.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import cn.net.yzl.oa.entity.AppStaffClockLogDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
public class ApplicationProperties {
	private String userLoginUrl;
	private String staffClockUrl;
	private String initStaffClockUrl;
	private String deviceListUrl;
	private String resetBindDeviceIdUrl;
	private String biSqlExecUrl;
	private Integer biSqlSourceId;
	@ToString.Exclude
	private String selectAppStaffClockLogSql;
	@ToString.Exclude
	private List<Address> addresses = new ArrayList<>();
	@ToString.Exclude
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
	@Accessors(chain = true)
	public static class UserInfo {
		private String userNo;
		private String password;
		private String username;
		private Integer status;
		private String message;
		private Address addr;
		private AppStaffClockLogDTO staffClock;
	}
}
