package com.example.seataclient.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 账单
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:55:23
 */
@Getter
@ToString
@Accessors(chain = true)
public class Bill {
	/** 账单主键 */
	private Integer billId;
	/** 账单编号 */
	private String billNum;
	/** 账户主键 */
	@Setter
	private Integer userId;
	/** 食品主键 */
	@Setter
	private Integer foodId;
	/** 下单时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime orderTime;
	/** 金额 */
	@Setter
	private Double amount;
	/** 数量 */
	@Setter
	private Long quantity;
}
