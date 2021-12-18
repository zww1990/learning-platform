package com.example.seataclient.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 账户
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Account {
	/** 主键 */
	private Integer userId;
	/** 姓名 */
	private String userName;
	/** 余额 */
	private Double balance;

}
