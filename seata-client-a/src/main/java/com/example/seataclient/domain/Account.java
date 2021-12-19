package com.example.seataclient.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 账户
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:54:38
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
