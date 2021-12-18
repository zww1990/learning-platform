package com.example.seataclient.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 账单
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Bill {
	/** 账单主键 */
	private Integer billId;
	/** 账单编号 */
	private String billNum;
	/** 账户主键 */
	private Integer userId;
	/** 食品主键 */
	private Integer foodId;
	/** 下单时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime orderTime;
	/** 金额 */
	private Double amount;
	/** 数量 */
	private Long quantity;
}
