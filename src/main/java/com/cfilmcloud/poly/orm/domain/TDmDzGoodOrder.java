package com.cfilmcloud.poly.orm.domain;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TDmDzGoodOrder entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_dm_dz_good_order")
public class TDmDzGoodOrder implements java.io.Serializable {
	// Fields
	private TDmDzGoodOrderId id;
	private String couponNum;
	private Date etlDataCycle;
	private String paymentName;
	private String cardNum;

	// Constructors
	/** default constructor */
	public TDmDzGoodOrder() {}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bookBizDate", column = @Column(name = "BOOK_BIZ_DATE", length = 10)),
			@AttributeOverride(name = "bookDate", column = @Column(name = "BOOK_DATE", length = 10)),
			@AttributeOverride(name = "orderId", column = @Column(name = "ORDER_ID", length = 50)),
			@AttributeOverride(name = "tspOrderCode", column = @Column(name = "TSP_ORDER_CODE", length = 50)),
			@AttributeOverride(name = "orderState", column = @Column(name = "ORDER_STATE", length = 10)),
			@AttributeOverride(name = "cinemaName", column = @Column(name = "CINEMA_NAME", length = 120)),
			@AttributeOverride(name = "cinemaCode", column = @Column(name = "CINEMA_CODE", length = 10)),
			@AttributeOverride(name = "createTime", column = @Column(name = "CREATE_TIME", length = 19)),
			@AttributeOverride(name = "goodName", column = @Column(name = "GOOD_NAME", length = 120)),
			@AttributeOverride(name = "amount", column = @Column(name = "AMOUNT", precision = 10, scale = 4)),
			@AttributeOverride(name = "realMoney", column = @Column(name = "REAL_MONEY", precision = 8, scale = 4)),
			@AttributeOverride(name = "mobile", column = @Column(name = "MOBILE", length = 20)),
			@AttributeOverride(name = "channelName", column = @Column(name = "CHANNEL_NAME", length = 120)),
			@AttributeOverride(name = "etlTime", column = @Column(name = "ETL_TIME", length = 19)),
			@AttributeOverride(name = "dataSource", column = @Column(name = "DATA_SOURCE", length = 50)) })
	public TDmDzGoodOrderId getId() {
		return this.id;
	}

	public void setId(TDmDzGoodOrderId id) {
		this.id = id;
	}

	@Column(name = "COUPON_NUM", length = 120)
	public String getCouponNum() {
		return this.couponNum;
	}

	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ETL_DATA_CYCLE", length = 10)
	public Date getEtlDataCycle() {
		return this.etlDataCycle;
	}

	public void setEtlDataCycle(Date etlDataCycle) {
		this.etlDataCycle = etlDataCycle;
	}

	@Column(name = "PAYMENT_NAME", length = 120)
	public String getPaymentName() {
		return this.paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	@Column(name = "CARD_NUM", length = 120)
	public String getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
}