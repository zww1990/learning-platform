package com.example.hello.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.net.yzl.oa.util.AESUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * UserLogin
 * 
 * @author zhang weiwei
 * @since 2022年8月13日,下午8:58:54
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserLogin {
	/** 0=(AM)上午 */
	public static final int AM = 0;
	/** 1=(PM)下午 */
	public static final int PM = 1;
	private String userNo;
	private String username;
	private String address;
	private BigDecimal longitude;
	private BigDecimal latitude;
	@JsonFormat(pattern = AESUtil.FORMAT, timezone = AESUtil.TIMEZONE)
	private LocalDateTime clockTime;
	private String[] dates;
	/** 0=(AM)上午，1=(PM)下午 */
	@JsonIgnore
	private int ampm;

	public LocalDateTime getClockTime() {
		return Optional.ofNullable(this.clockTime).orElseGet(LocalDateTime::now);
	}
}
