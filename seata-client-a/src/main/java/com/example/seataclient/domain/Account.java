package com.example.seataclient.domain;

/**
 * 账户
 */
public class Account {
	private Integer userId;
	private String userName;
	private Double balance;

	public Account() {
		super();
	}

	/**
	 * @param userId   主键
	 * @param userName 姓名
	 * @param balance  余额
	 */
	public Account(Integer userId, String userName, Double balance) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.balance = balance;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return String.format("Account [userId=%s, userName=%s, balance=%s]", userId, userName, balance);
	}
}
