package com.example.test.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.net.yzl.oa.util.AESUtil;

public class UserLogin {
	private String userNo;
	private String username;
	private String password;
	private String address;
	private BigDecimal longitude;
	private BigDecimal latitude;
	@JsonFormat(pattern = AESUtil.FORMAT, timezone = AESUtil.TIMEZONE)
	private LocalDateTime clockTime;

	public LocalDateTime getClockTime() {
		return Optional.ofNullable(this.clockTime).orElseGet(LocalDateTime::now);
	}

	public String getUserNo() {
		return userNo;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
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

	public UserLogin setUserNo(String userNo) {
		this.userNo = userNo;
		return this;
	}

	public UserLogin setUsername(String username) {
		this.username = username;
		return this;
	}

	public UserLogin setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserLogin setAddress(String address) {
		this.address = address;
		return this;
	}

	public UserLogin setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
		return this;
	}

	public UserLogin setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
		return this;
	}

	public UserLogin setClockTime(LocalDateTime clockTime) {
		this.clockTime = clockTime;
		return this;
	}

	@Override
	public String toString() {
		return String.format(
				"UserLogin [userNo=%s, username=%s, password=%s, address=%s, longitude=%s, latitude=%s, clockTime=%s]",
				userNo, username, password, address, longitude, latitude, clockTime);
	}
}
