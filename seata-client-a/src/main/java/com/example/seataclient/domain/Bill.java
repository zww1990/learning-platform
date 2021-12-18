package com.example.seataclient.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 账单
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
	private LocalDateTime orderTime;
	/** 金额 */
	@Setter
	private Double amount;
	/** 数量 */
	@Setter
	private Long quantity;
}
