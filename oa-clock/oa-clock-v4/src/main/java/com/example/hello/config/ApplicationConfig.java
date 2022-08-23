package com.example.hello.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import cn.net.yzl.oa.entity.AppStaffClockLogDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * ApplicationConfig
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午4:23:08
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
public class ApplicationConfig {
	private String staffClockUrl;
	private String initStaffClockUrl;
	private String deviceListUrl;
	private String resetBindDeviceIdUrl;
	private String createOaAttendUrl;
	private String biSqlExecUrl;
	private Integer biSqlSourceId;
	@ToString.Exclude
	private String selectAppStaffClockLogSql;
	@ToString.Exclude
	private List<Address> addresses = new ArrayList<>();
	@ToString.Exclude
	private List<UserInfo> users = new ArrayList<>();
	private JobConfig jobConfig = new JobConfig();
	private String hostAddress;

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
		private Address addr;
		private AppStaffClockLogDTO staffClock;
	}

	@Getter
	@Setter
	@ToString
	public static class JobConfig {
		private String jobKey;
		private String triggerKey;
		private String cronExpression;
		private String jobDataKey;
	}
}
