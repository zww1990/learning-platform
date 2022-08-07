package com.example.hello.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.net.yzl.oa.util.AESUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * UserLogin
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午4:23:47
 */
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
