package com.example.hello.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.net.yzl.oa.entity.AppStaffClockLogDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * ApplicationProperties
 * 
 * @author zhang weiwei
 * @since 2022年8月13日,下午8:58:14
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
public class ApplicationProperties {
	private String staffClockUrl;
	private String initStaffClockUrl;
	private String deviceListUrl;
	private String resetBindDeviceIdUrl;
	private String createOaAttendUrl;
	private String biSqlExecUrl;
	@ToString.Exclude
	private Integer biSqlSourceId;
	@ToString.Exclude
	private String selectAppStaffClockLogSql;
	@ToString.Exclude
	private List<Address> addresses = new ArrayList<>();
	@ToString.Exclude
	private List<UserInfo> users = new ArrayList<>();
	private Task task = new Task();

	@Getter
	@Setter
	@ToString
	public static class Task {
		private String cron;
		private Integer amMax;
		private Integer pmMax;
	}

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
		private String username;
		private Integer status;
		private String message;
		@JsonIgnore
		private boolean enabled;
		@JsonIgnore
		private boolean pmOn;
		@JsonIgnore
		private String email;
		private Address addr;
		private AppStaffClockLogDTO staffClock;
	}
}
