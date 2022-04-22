package com.example.test.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.net.yzl.oa.util.AESUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserLogin {
	private String userNo;
	private String username;
	private String address;
	private BigDecimal longitude;
	private BigDecimal latitude;
	@JsonFormat(pattern = AESUtil.FORMAT, timezone = AESUtil.TIMEZONE)
	private LocalDateTime clockTime;
	private String[] dates;

	public LocalDateTime getClockTime() {
		return Optional.ofNullable(this.clockTime).orElseGet(LocalDateTime::now);
	}
}
