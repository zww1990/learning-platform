package com.cfilmcloud.poly.orm.domain;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TDmDzIncomeDetailId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Embeddable
public class TDmDzIncomeDetailId implements java.io.Serializable {
	// Fields
	private Date bookBizDate;
	private String cinemaName;
	private String cinemaCode;
	private String mobile;
	private String channelName;
	private Long orders;
	private Double faceMoney;
	private Double goodMoney;
	private Double tspServiceFee;
	private Double totalMoney;
	private String cityName;
	private String provinceName;
	private String areaName;
	private Timestamp etlTime;
	private String dataSource;

	// Constructors
	/** default constructor */
	public TDmDzIncomeDetailId() {}

	// Property accessors
	@Temporal(TemporalType.DATE)
	@Column(name = "BOOK_BIZ_DATE", length = 10)
	public Date getBookBizDate() {
		return this.bookBizDate;
	}

	public void setBookBizDate(Date bookBizDate) {
		this.bookBizDate = bookBizDate;
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

	@Column(name = "ORDERS", nullable = false)
	public Long getOrders() {
		return this.orders;
	}

	public void setOrders(Long orders) {
		this.orders = orders;
	}

	@Column(name = "FACE_MONEY", precision = 40, scale = 4)
	public Double getFaceMoney() {
		return this.faceMoney;
	}

	public void setFaceMoney(Double faceMoney) {
		this.faceMoney = faceMoney;
	}

	@Column(name = "GOOD_MONEY", precision = 30, scale = 4)
	public Double getGoodMoney() {
		return this.goodMoney;
	}

	public void setGoodMoney(Double goodMoney) {
		this.goodMoney = goodMoney;
	}

	@Column(name = "TSP_SERVICE_FEE", precision = 30, scale = 4)
	public Double getTspServiceFee() {
		return this.tspServiceFee;
	}

	public void setTspServiceFee(Double tspServiceFee) {
		this.tspServiceFee = tspServiceFee;
	}

	@Column(name = "TOTAL_MONEY", precision = 31, scale = 4)
	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "CITY_NAME", length = 50)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "PROVINCE_NAME", length = 50)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "AREA_NAME", length = 50)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
		if (!(other instanceof TDmDzIncomeDetailId))
			return false;
		TDmDzIncomeDetailId castOther = (TDmDzIncomeDetailId) other;
		return ((this.getBookBizDate() == castOther.getBookBizDate()) || (this.getBookBizDate() != null
				&& castOther.getBookBizDate() != null && this.getBookBizDate().equals(castOther.getBookBizDate())))
				&& ((this.getCinemaName() == castOther.getCinemaName()) || (this.getCinemaName() != null
						&& castOther.getCinemaName() != null && this.getCinemaName().equals(castOther.getCinemaName())))
				&& ((this.getCinemaCode() == castOther.getCinemaCode()) || (this.getCinemaCode() != null
						&& castOther.getCinemaCode() != null && this.getCinemaCode().equals(castOther.getCinemaCode())))
				&& ((this.getMobile() == castOther.getMobile()) || (this.getMobile() != null
						&& castOther.getMobile() != null && this.getMobile().equals(castOther.getMobile())))
				&& ((this.getChannelName() == castOther.getChannelName())
						|| (this.getChannelName() != null && castOther.getChannelName() != null
								&& this.getChannelName().equals(castOther.getChannelName())))
				&& ((this.getOrders() == castOther.getOrders()) || (this.getOrders() != null
						&& castOther.getOrders() != null && this.getOrders().equals(castOther.getOrders())))
				&& ((this.getFaceMoney() == castOther.getFaceMoney()) || (this.getFaceMoney() != null
						&& castOther.getFaceMoney() != null && this.getFaceMoney().equals(castOther.getFaceMoney())))
				&& ((this.getGoodMoney() == castOther.getGoodMoney()) || (this.getGoodMoney() != null
						&& castOther.getGoodMoney() != null && this.getGoodMoney().equals(castOther.getGoodMoney())))
				&& ((this.getTspServiceFee() == castOther.getTspServiceFee())
						|| (this.getTspServiceFee() != null && castOther.getTspServiceFee() != null
								&& this.getTspServiceFee().equals(castOther.getTspServiceFee())))
				&& ((this.getTotalMoney() == castOther.getTotalMoney()) || (this.getTotalMoney() != null
						&& castOther.getTotalMoney() != null && this.getTotalMoney().equals(castOther.getTotalMoney())))
				&& ((this.getCityName() == castOther.getCityName()) || (this.getCityName() != null
						&& castOther.getCityName() != null && this.getCityName().equals(castOther.getCityName())))
				&& ((this.getProvinceName() == castOther.getProvinceName())
						|| (this.getProvinceName() != null && castOther.getProvinceName() != null
								&& this.getProvinceName().equals(castOther.getProvinceName())))
				&& ((this.getAreaName() == castOther.getAreaName()) || (this.getAreaName() != null
						&& castOther.getAreaName() != null && this.getAreaName().equals(castOther.getAreaName())))
				&& ((this.getEtlTime() == castOther.getEtlTime()) || (this.getEtlTime() != null
						&& castOther.getEtlTime() != null && this.getEtlTime().equals(castOther.getEtlTime())))
				&& ((this.getDataSource() == castOther.getDataSource())
						|| (this.getDataSource() != null && castOther.getDataSource() != null
								&& this.getDataSource().equals(castOther.getDataSource())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getBookBizDate() == null ? 0 : this.getBookBizDate().hashCode());
		result = 37 * result + (getCinemaName() == null ? 0 : this.getCinemaName().hashCode());
		result = 37 * result + (getCinemaCode() == null ? 0 : this.getCinemaCode().hashCode());
		result = 37 * result + (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result + (getChannelName() == null ? 0 : this.getChannelName().hashCode());
		result = 37 * result + (getOrders() == null ? 0 : this.getOrders().hashCode());
		result = 37 * result + (getFaceMoney() == null ? 0 : this.getFaceMoney().hashCode());
		result = 37 * result + (getGoodMoney() == null ? 0 : this.getGoodMoney().hashCode());
		result = 37 * result + (getTspServiceFee() == null ? 0 : this.getTspServiceFee().hashCode());
		result = 37 * result + (getTotalMoney() == null ? 0 : this.getTotalMoney().hashCode());
		result = 37 * result + (getCityName() == null ? 0 : this.getCityName().hashCode());
		result = 37 * result + (getProvinceName() == null ? 0 : this.getProvinceName().hashCode());
		result = 37 * result + (getAreaName() == null ? 0 : this.getAreaName().hashCode());
		result = 37 * result + (getEtlTime() == null ? 0 : this.getEtlTime().hashCode());
		result = 37 * result + (getDataSource() == null ? 0 : this.getDataSource().hashCode());
		return result;
	}
}