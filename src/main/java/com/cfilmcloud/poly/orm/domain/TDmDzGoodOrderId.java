package com.cfilmcloud.poly.orm.domain;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TDmDzGoodOrderId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Embeddable
public class TDmDzGoodOrderId implements java.io.Serializable {
	// Fields
	private Date bookBizDate;
	private Date bookDate;
	private String orderId;
	private String tspOrderCode;
	private String orderState;
	private String cinemaName;
	private String cinemaCode;
	private Timestamp createTime;
	private String goodName;
	private Double amount;
	private Double realMoney;
	private String mobile;
	private String channelName;
	private Timestamp etlTime;
	private String dataSource;

	// Constructors
	/** default constructor */
	public TDmDzGoodOrderId() {}

	// Property accessors
	@Temporal(TemporalType.DATE)
	@Column(name = "BOOK_BIZ_DATE", length = 10)
	public Date getBookBizDate() {
		return this.bookBizDate;
	}

	public void setBookBizDate(Date bookBizDate) {
		this.bookBizDate = bookBizDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BOOK_DATE", length = 10)
	public Date getBookDate() {
		return this.bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	@Column(name = "ORDER_ID", length = 50)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "TSP_ORDER_CODE", length = 50)
	public String getTspOrderCode() {
		return this.tspOrderCode;
	}

	public void setTspOrderCode(String tspOrderCode) {
		this.tspOrderCode = tspOrderCode;
	}

	@Column(name = "ORDER_STATE", length = 10)
	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	@Column(name = "CINEMA_NAME", length = 120)
	public String getCinemaName() {
		return this.cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	@Column(name = "CINEMA_CODE", length = 10)
	public String getCinemaCode() {
		return this.cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	@Column(name = "CREATE_TIME", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "GOOD_NAME", length = 120)
	public String getGoodName() {
		return this.goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	@Column(name = "AMOUNT", precision = 10, scale = 4)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "REAL_MONEY", precision = 8, scale = 4)
	public Double getRealMoney() {
		return this.realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	@Column(name = "MOBILE", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "CHANNEL_NAME", length = 120)
	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Column(name = "ETL_TIME", length = 19)
	public Timestamp getEtlTime() {
		return this.etlTime;
	}

	public void setEtlTime(Timestamp etlTime) {
		this.etlTime = etlTime;
	}

	@Column(name = "DATA_SOURCE", length = 50)
	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TDmDzGoodOrderId))
			return false;
		TDmDzGoodOrderId castOther = (TDmDzGoodOrderId) other;
		return ((this.getBookBizDate() == castOther.getBookBizDate()) || (this.getBookBizDate() != null
				&& castOther.getBookBizDate() != null && this.getBookBizDate().equals(castOther.getBookBizDate())))
				&& ((this.getBookDate() == castOther.getBookDate()) || (this.getBookDate() != null
						&& castOther.getBookDate() != null && this.getBookDate().equals(castOther.getBookDate())))
				&& ((this.getOrderId() == castOther.getOrderId()) || (this.getOrderId() != null
						&& castOther.getOrderId() != null && this.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getTspOrderCode() == castOther.getTspOrderCode())
						|| (this.getTspOrderCode() != null && castOther.getTspOrderCode() != null
								&& this.getTspOrderCode().equals(castOther.getTspOrderCode())))
				&& ((this.getOrderState() == castOther.getOrderState()) || (this.getOrderState() != null
						&& castOther.getOrderState() != null && this.getOrderState().equals(castOther.getOrderState())))
				&& ((this.getCinemaName() == castOther.getCinemaName()) || (this.getCinemaName() != null
						&& castOther.getCinemaName() != null && this.getCinemaName().equals(castOther.getCinemaName())))
				&& ((this.getCinemaCode() == castOther.getCinemaCode()) || (this.getCinemaCode() != null
						&& castOther.getCinemaCode() != null && this.getCinemaCode().equals(castOther.getCinemaCode())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this.getCreateTime() != null
						&& castOther.getCreateTime() != null && this.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getGoodName() == castOther.getGoodName()) || (this.getGoodName() != null
						&& castOther.getGoodName() != null && this.getGoodName().equals(castOther.getGoodName())))
				&& ((this.getAmount() == castOther.getAmount()) || (this.getAmount() != null
						&& castOther.getAmount() != null && this.getAmount().equals(castOther.getAmount())))
				&& ((this.getRealMoney() == castOther.getRealMoney()) || (this.getRealMoney() != null
						&& castOther.getRealMoney() != null && this.getRealMoney().equals(castOther.getRealMoney())))
				&& ((this.getMobile() == castOther.getMobile()) || (this.getMobile() != null
						&& castOther.getMobile() != null && this.getMobile().equals(castOther.getMobile())))
				&& ((this.getChannelName() == castOther.getChannelName())
						|| (this.getChannelName() != null && castOther.getChannelName() != null
								&& this.getChannelName().equals(castOther.getChannelName())))
				&& ((this.getEtlTime() == castOther.getEtlTime()) || (this.getEtlTime() != null
						&& castOther.getEtlTime() != null && this.getEtlTime().equals(castOther.getEtlTime())))
				&& ((this.getDataSource() == castOther.getDataSource())
						|| (this.getDataSource() != null && castOther.getDataSource() != null
								&& this.getDataSource().equals(castOther.getDataSource())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getBookBizDate() == null ? 0 : this.getBookBizDate().hashCode());
		result = 37 * result + (getBookDate() == null ? 0 : this.getBookDate().hashCode());
		result = 37 * result + (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result + (getTspOrderCode() == null ? 0 : this.getTspOrderCode().hashCode());
		result = 37 * result + (getOrderState() == null ? 0 : this.getOrderState().hashCode());
		result = 37 * result + (getCinemaName() == null ? 0 : this.getCinemaName().hashCode());
		result = 37 * result + (getCinemaCode() == null ? 0 : this.getCinemaCode().hashCode());
		result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode());
		result = 37 * result + (getGoodName() == null ? 0 : this.getGoodName().hashCode());
		result = 37 * result + (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37 * result + (getRealMoney() == null ? 0 : this.getRealMoney().hashCode());
		result = 37 * result + (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result + (getChannelName() == null ? 0 : this.getChannelName().hashCode());
		result = 37 * result + (getEtlTime() == null ? 0 : this.getEtlTime().hashCode());
		result = 37 * result + (getDataSource() == null ? 0 : this.getDataSource().hashCode());
		return result;
	}
}