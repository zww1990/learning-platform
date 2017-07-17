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
 * TDmDzTicketOrder entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_dm_dz_ticket_order")
public class TDmDzTicketOrder implements java.io.Serializable {
	// Fields
	private TDmDzTicketOrderId id;
	private String paymentName;
	private String cardNum;
	private String couponNum;
	private Date etlDataCycle;
	private String printNo;

	// Constructors
	/** default constructor */
	public TDmDzTicketOrder() {}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bookBizDate", column = @Column(name = "BOOK_BIZ_DATE", length = 10)),
			@AttributeOverride(name = "bookDate", column = @Column(name = "BOOK_DATE", length = 10)),
			@AttributeOverride(name = "orderId", column = @Column(name = "ORDER_ID", length = 50)),
			@AttributeOverride(name = "tspOrderCode", column = @Column(name = "TSP_ORDER_CODE", length = 50)),
			@AttributeOverride(name = "createTime", column = @Column(name = "CREATE_TIME", length = 50)),
			@AttributeOverride(name = "orderState", column = @Column(name = "ORDER_STATE", length = 10)),
			@AttributeOverride(name = "filmCode", column = @Column(name = "FILM_CODE", length = 20)),
			@AttributeOverride(name = "filmName", column = @Column(name = "FILM_NAME", length = 120)),
			@AttributeOverride(name = "showDate", column = @Column(name = "SHOW_DATE", length = 19)),
			@AttributeOverride(name = "channelName", column = @Column(name = "CHANNEL_NAME", length = 120)),
			@AttributeOverride(name = "cinemaCode", column = @Column(name = "CINEMA_CODE", length = 10)),
			@AttributeOverride(name = "cinemaName", column = @Column(name = "CINEMA_NAME", length = 120)),
			@AttributeOverride(name = "screenId", column = @Column(name = "SCREEN_ID", length = 10)),
			@AttributeOverride(name = "screenName", column = @Column(name = "SCREEN_NAME", length = 120)),
			@AttributeOverride(name = "mobile", column = @Column(name = "MOBILE", length = 20)),
			@AttributeOverride(name = "seatInfo", column = @Column(name = "SEAT_INFO", length = 120)),
			@AttributeOverride(name = "tickets", column = @Column(name = "TICKETS")),
			@AttributeOverride(name = "faceMoney", column = @Column(name = "FACE_MONEY", precision = 8, scale = 4)),
			@AttributeOverride(name = "ownerFaceMoney", column = @Column(name = "OWNER_FACE_MONEY", precision = 8, scale = 4)),
			@AttributeOverride(name = "tspServiceFee", column = @Column(name = "TSP_SERVICE_FEE", precision = 8, scale = 4)),
			@AttributeOverride(name = "platformServiceFee", column = @Column(name = "PLATFORM_SERVICE_FEE", precision = 8, scale = 4)),
			@AttributeOverride(name = "etlTime", column = @Column(name = "ETL_TIME", length = 19)),
			@AttributeOverride(name = "dataSource", column = @Column(name = "DATA_SOURCE", nullable = false, length = 50)) })
	public TDmDzTicketOrderId getId() {
		return this.id;
	}

	public void setId(TDmDzTicketOrderId id) {
		this.id = id;
	}

	@Column(name = "PRINT_NO", length = 20)
	public String getPrintNo() {
		return this.printNo;
	}

	public void setPrintNo(String printNo) {
		this.printNo = printNo;
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
}