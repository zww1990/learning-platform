package com.cfilmcloud.poly.orm.domain;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TDmDzCardRechargeId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Embeddable
public class TDmDzCardRechargeId implements java.io.Serializable {
	// Fields
	private Date bookBizDate;
	private String rechargeOrderId;
	private String orderState;
	private Timestamp rechargeTime;
	private String cinemaName;
	private String cinemaCode;
	private String packageName;
	private Double rechargeMoney;
	private Double paidMoney;
	private String cardNum;
	private String userId;
	private String phone;
	private Date etlDataCycle;
	private Timestamp etlTime;
	private String dataSource;

	// Constructors
	/** default constructor */
	public TDmDzCardRechargeId() {}

	// Property accessors
	@Temporal(TemporalType.DATE)
	@Column(name = "BOOK_BIZ_DATE", length = 10)
	public Date getBookBizDate() {
		return this.bookBizDate;
	}

	public void setBookBizDate(Date bookBizDate) {
		this.bookBizDate = bookBizDate;
	}

	@Column(name = "RECHARGE_ORDER_ID", length = 50)
	public String getRechargeOrderId() {
		return this.rechargeOrderId;
	}

	public void setRechargeOrderId(String rechargeOrderId) {
		this.rechargeOrderId = rechargeOrderId;
	}

	@Column(name = "ORDER_STATE", length = 10)
	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	@Column(name = "RECHARGE_TIME", length = 19)
	public Timestamp getRechargeTime() {
		return this.rechargeTime;
	}

	public void setRechargeTime(Timestamp rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	@Column(name = "CINEMA_NAME", length = 50)
	public String getCinemaName() {
		return this.cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	@Column(name = "CINEMA_CODE", length = 50)
	public String getCinemaCode() {
		return this.cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	@Column(name = "PACKAGE_NAME", length = 120)
	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Column(name = "RECHARGE_MONEY", precision = 30, scale = 4)
	public Double getRechargeMoney() {
		return this.rechargeMoney;
	}

	public void setRechargeMoney(Double rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	@Column(name = "PAID_MONEY", precision = 30, scale = 4)
	public Double getPaidMoney() {
		return this.paidMoney;
	}

	public void setPaidMoney(Double paidMoney) {
		this.paidMoney = paidMoney;
	}

	@Column(name = "CARD_NUM", length = 50)
	public String getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	@Column(name = "USER_ID", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "PHONE", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ETL_DATA_CYCLE", length = 10)
	public Date getEtlDataCycle() {
		return this.etlDataCycle;
	}

	public void setEtlDataCycle(Date etlDataCycle) {
		this.etlDataCycle = etlDataCycle;
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
		if (!(other instanceof TDmDzCardRechargeId))
			return false;
		TDmDzCardRechargeId castOther = (TDmDzCardRechargeId) other;
		return ((this.getBookBizDate() == castOther.getBookBizDate()) || (this.getBookBizDate() != null
				&& castOther.getBookBizDate() != null && this.getBookBizDate().equals(castOther.getBookBizDate())))
				&& ((this.getRechargeOrderId() == castOther.getRechargeOrderId())
						|| (this.getRechargeOrderId() != null && castOther.getRechargeOrderId() != null
								&& this.getRechargeOrderId().equals(castOther.getRechargeOrderId())))
				&& ((this.getOrderState() == castOther.getOrderState()) || (this.getOrderState() != null
						&& castOther.getOrderState() != null && this.getOrderState().equals(castOther.getOrderState())))
				&& ((this.getRechargeTime() == castOther.getRechargeTime())
						|| (this.getRechargeTime() != null && castOther.getRechargeTime() != null
								&& this.getRechargeTime().equals(castOther.getRechargeTime())))
				&& ((this.getCinemaName() == castOther.getCinemaName()) || (this.getCinemaName() != null
						&& castOther.getCinemaName() != null && this.getCinemaName().equals(castOther.getCinemaName())))
				&& ((this.getCinemaCode() == castOther.getCinemaCode()) || (this.getCinemaCode() != null
						&& castOther.getCinemaCode() != null && this.getCinemaCode().equals(castOther.getCinemaCode())))
				&& ((this.getPackageName() == castOther.getPackageName())
						|| (this.getPackageName() != null && castOther.getPackageName() != null
								&& this.getPackageName().equals(castOther.getPackageName())))
				&& ((this.getRechargeMoney() == castOther.getRechargeMoney())
						|| (this.getRechargeMoney() != null && castOther.getRechargeMoney() != null
								&& this.getRechargeMoney().equals(castOther.getRechargeMoney())))
				&& ((this.getPaidMoney() == castOther.getPaidMoney()) || (this.getPaidMoney() != null
						&& castOther.getPaidMoney() != null && this.getPaidMoney().equals(castOther.getPaidMoney())))
				&& ((this.getCardNum() == castOther.getCardNum()) || (this.getCardNum() != null
						&& castOther.getCardNum() != null && this.getCardNum().equals(castOther.getCardNum())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getPhone() == castOther.getPhone()) || (this.getPhone() != null
						&& castOther.getPhone() != null && this.getPhone().equals(castOther.getPhone())))
				&& ((this.getEtlDataCycle() == castOther.getEtlDataCycle())
						|| (this.getEtlDataCycle() != null && castOther.getEtlDataCycle() != null
								&& this.getEtlDataCycle().equals(castOther.getEtlDataCycle())))
				&& ((this.getEtlTime() == castOther.getEtlTime()) || (this.getEtlTime() != null
						&& castOther.getEtlTime() != null && this.getEtlTime().equals(castOther.getEtlTime())))
				&& ((this.getDataSource() == castOther.getDataSource())
						|| (this.getDataSource() != null && castOther.getDataSource() != null
								&& this.getDataSource().equals(castOther.getDataSource())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getBookBizDate() == null ? 0 : this.getBookBizDate().hashCode());
		result = 37 * result + (getRechargeOrderId() == null ? 0 : this.getRechargeOrderId().hashCode());
		result = 37 * result + (getOrderState() == null ? 0 : this.getOrderState().hashCode());
		result = 37 * result + (getRechargeTime() == null ? 0 : this.getRechargeTime().hashCode());
		result = 37 * result + (getCinemaName() == null ? 0 : this.getCinemaName().hashCode());
		result = 37 * result + (getCinemaCode() == null ? 0 : this.getCinemaCode().hashCode());
		result = 37 * result + (getPackageName() == null ? 0 : this.getPackageName().hashCode());
		result = 37 * result + (getRechargeMoney() == null ? 0 : this.getRechargeMoney().hashCode());
		result = 37 * result + (getPaidMoney() == null ? 0 : this.getPaidMoney().hashCode());
		result = 37 * result + (getCardNum() == null ? 0 : this.getCardNum().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result + (getEtlDataCycle() == null ? 0 : this.getEtlDataCycle().hashCode());
		result = 37 * result + (getEtlTime() == null ? 0 : this.getEtlTime().hashCode());
		result = 37 * result + (getDataSource() == null ? 0 : this.getDataSource().hashCode());
		return result;
	}
}