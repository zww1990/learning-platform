package com.example.test.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import cn.net.yzl.oa.entity.AppStaffClockLogDTO;

@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
	private String userLoginUrl;
	private String staffClockUrl;
	private String initStaffClockUrl;
	private String deviceListUrl;
	private String resetBindDeviceIdUrl;
	private String biSqlExecUrl;
	private Integer biSqlSourceId;
	private String selectAppStaffClockLogSql;
	private List<Address> addresses = new ArrayList<>();
	private List<UserInfo> users = new ArrayList<>();

	public String getUserLoginUrl() {
		return userLoginUrl;
	}

	public String getStaffClockUrl() {
		return staffClockUrl;
	}

	public String getInitStaffClockUrl() {
		return initStaffClockUrl;
	}

	public String getDeviceListUrl() {
		return deviceListUrl;
	}

	public String getResetBindDeviceIdUrl() {
		return resetBindDeviceIdUrl;
	}

	public String getBiSqlExecUrl() {
		return biSqlExecUrl;
	}

	public Integer getBiSqlSourceId() {
		return biSqlSourceId;
	}

	public String getSelectAppStaffClockLogSql() {
		return selectAppStaffClockLogSql;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public List<UserInfo> getUsers() {
		return users;
	}

	public ApplicationProperties setUserLoginUrl(String userLoginUrl) {
		this.userLoginUrl = userLoginUrl;
		return this;
	}

	public ApplicationProperties setStaffClockUrl(String staffClockUrl) {
		this.staffClockUrl = staffClockUrl;
		return this;
	}

	public ApplicationProperties setInitStaffClockUrl(String initStaffClockUrl) {
		this.initStaffClockUrl = initStaffClockUrl;
		return this;
	}

	public ApplicationProperties setDeviceListUrl(String deviceListUrl) {
		this.deviceListUrl = deviceListUrl;
		return this;
	}

	public ApplicationProperties setResetBindDeviceIdUrl(String resetBindDeviceIdUrl) {
		this.resetBindDeviceIdUrl = resetBindDeviceIdUrl;
		return this;
	}

	public ApplicationProperties setBiSqlExecUrl(String biSqlExecUrl) {
		this.biSqlExecUrl = biSqlExecUrl;
		return this;
	}

	public ApplicationProperties setBiSqlSourceId(Integer biSqlSourceId) {
		this.biSqlSourceId = biSqlSourceId;
		return this;
	}

	public ApplicationProperties setSelectAppStaffClockLogSql(String selectAppStaffClockLogSql) {
		this.selectAppStaffClockLogSql = selectAppStaffClockLogSql;
		return this;
	}

	public ApplicationProperties setAddresses(List<Address> addresses) {
		this.addresses = addresses;
		return this;
	}

	public ApplicationProperties setUsers(List<UserInfo> users) {
		this.users = users;
		return this;
	}

	@Override
	public String toString() {
		return String.format(
				"ApplicationProperties [userLoginUrl=%s, staffClockUrl=%s, initStaffClockUrl=%s, deviceListUrl=%s, resetBindDeviceIdUrl=%s, biSqlExecUrl=%s, biSqlSourceId=%s]",
				userLoginUrl, staffClockUrl, initStaffClockUrl, deviceListUrl, resetBindDeviceIdUrl, biSqlExecUrl,
				biSqlSourceId);
	}

	public static class Address {
		private Integer id;
		private String address;
		private BigDecimal longitude;
		private BigDecimal latitude;

		public Integer getId() {
			return id;
		}

		public String getAddress() {
			return address;
		}

		public BigDecimal getLongitude() {
			return longitude;
		}

		public BigDecimal getLatitude() {
			return latitude;
		}

		public Address setId(Integer id) {
			this.id = id;
			return this;
		}

		public Address setAddress(String address) {
			this.address = address;
			return this;
		}

		public Address setLongitude(BigDecimal longitude) {
			this.longitude = longitude;
			return this;
		}

		public Address setLatitude(BigDecimal latitude) {
			this.latitude = latitude;
			return this;
		}

		@Override
		public String toString() {
			return String.format("Address [id=%s, address=%s, longitude=%s, latitude=%s]", id, address, longitude,
					latitude);
		}
	}

	public static class UserInfo {
		private String userNo;
		private String password;
		private String username;
		private Integer status;
		private String message;
		private Address addr;
		private AppStaffClockLogDTO staffClock;

		public String getUserNo() {
			return userNo;
		}

		public String getPassword() {
			return password;
		}

		public String getUsername() {
			return username;
		}

		public Integer getStatus() {
			return status;
		}

		public String getMessage() {
			return message;
		}

		public Address getAddr() {
			return addr;
		}

		public AppStaffClockLogDTO getStaffClock() {
			return staffClock;
		}

		public UserInfo setUserNo(String userNo) {
			this.userNo = userNo;
			return this;
		}

		public UserInfo setPassword(String password) {
			this.password = password;
			return this;
		}

		public UserInfo setUsername(String username) {
			this.username = username;
			return this;
		}

		public UserInfo setStatus(Integer status) {
			this.status = status;
			return this;
		}

		public UserInfo setMessage(String message) {
			this.message = message;
			return this;
		}

		public UserInfo setAddr(Address addr) {
			this.addr = addr;
			return this;
		}

		public UserInfo setStaffClock(AppStaffClockLogDTO staffClock) {
			this.staffClock = staffClock;
			return this;
		}

		@Override
		public String toString() {
			return String.format(
					"UserInfo [userNo=%s, password=%s, username=%s, status=%s, message=%s, addr=%s, staffClock=%s]",
					userNo, password, username, status, message, addr, staffClock);
		}
	}
}
