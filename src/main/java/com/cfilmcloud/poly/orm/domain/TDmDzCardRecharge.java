package com.cfilmcloud.poly.orm.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TDmDzCardRecharge entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_dm_dz_card_recharge")
public class TDmDzCardRecharge implements java.io.Serializable {
	// Fields
	private TDmDzCardRechargeId id;
	private String giftName;
	private Integer giftValue;
	private String paymentName;

	// Constructors
	/** default constructor */
	public TDmDzCardRecharge() {}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bookBizDate", column = @Column(name = "BOOK_BIZ_DATE", length = 10)),
			@AttributeOverride(name = "rechargeOrderId", column = @Column(name = "RECHARGE_ORDER_ID", length = 50)),
			@AttributeOverride(name = "orderState", column = @Column(name = "ORDER_STATE", length = 10)),
			@AttributeOverride(name = "rechargeTime", column = @Column(name = "RECHARGE_TIME", length = 19)),
			@AttributeOverride(name = "cinemaName", column = @Column(name = "CINEMA_NAME", length = 50)),
			@AttributeOverride(name = "cinemaCode", column = @Column(name = "CINEMA_CODE", length = 50)),
			@AttributeOverride(name = "packageName", column = @Column(name = "PACKAGE_NAME", length = 120)),
			@AttributeOverride(name = "rechargeMoney", column = @Column(name = "RECHARGE_MONEY", precision = 30, scale = 4)),
			@AttributeOverride(name = "paidMoney", column = @Column(name = "PAID_MONEY", precision = 30, scale = 4)),
			@AttributeOverride(name = "cardNum", column = @Column(name = "CARD_NUM", length = 50)),
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", length = 50)),
			@AttributeOverride(name = "phone", column = @Column(name = "PHONE", length = 50)),
			@AttributeOverride(name = "etlDataCycle", column = @Column(name = "ETL_DATA_CYCLE", length = 10)),
			@AttributeOverride(name = "etlTime", column = @Column(name = "ETL_TIME", length = 19)),
			@AttributeOverride(name = "dataSource", column = @Column(name = "DATA_SOURCE", length = 50)) })
	public TDmDzCardRechargeId getId() {
		return this.id;
	}

	public void setId(TDmDzCardRechargeId id) {
		this.id = id;
	}

	@Column(name = "GIFT_NAME", length = 120)
	public String getGiftName() {
		return this.giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	@Column(name = "GIFT_VALUE")
	public Integer getGiftValue() {
		return this.giftValue;
	}

	public void setGiftValue(Integer giftValue) {
		this.giftValue = giftValue;
	}

	@Column(name = "PAYMENT_NAME", length = 120)
	public String getPaymentName() {
		return this.paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
}