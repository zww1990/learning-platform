package com.cfilmcloud.poly.orm.domain;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TDmDzGoodSalesId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Embeddable
public class TDmDzGoodSalesId implements java.io.Serializable {
	// Fields
	private Date bookBizDate;
	private String cinemaCode;
	private String cinemaName;
	private String channelName;
	private String goodName;
	private String unit;
	private Double amount;
	private Double realMoney;
	private Double unitPrice;
	private String cityName;
	private String provinceName;
	private String areaName;
	private Timestamp etlTime;
	private String dataSource;

	// Constructors
	/** default constructor */
	public TDmDzGoodSalesId() {}

	// Property accessors
	@Temporal(TemporalType.DATE)
	@Column(name = "BOOK_BIZ_DATE", length = 10)
	public Date getBookBizDate() {
		return this.bookBizDate;
	}

	public void setBookBizDate(Date bookBizDate) {
		this.bookBizDate = bookBizDate;
	}

	@Column(name = "CINEMA_CODE", length = 10)
	public String getCinemaCode() {
		return this.cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	@Column(name = "CINEMA_NAME", length = 120)
	public String getCinemaName() {
		return this.cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	@Column(name = "CHANNEL_NAME", length = 120)
	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Column(name = "GOOD_NAME", length = 120)
	public String getGoodName() {
		return this.goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	@Column(name = "UNIT", length = 80)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "AMOUNT", precision = 32, scale = 4)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "REAL_MONEY", precision = 30, scale = 4)
	public Double getRealMoney() {
		return this.realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	@Column(name = "UNIT_PRICE", precision = 8, scale = 4)
	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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
		if (!(other instanceof TDmDzGoodSalesId))
			return false;
		TDmDzGoodSalesId castOther = (TDmDzGoodSalesId) other;
		return ((this.getBookBizDate() == castOther.getBookBizDate()) || (this.getBookBizDate() != null
				&& castOther.getBookBizDate() != null && this.getBookBizDate().equals(castOther.getBookBizDate())))
				&& ((this.getCinemaCode() == castOther.getCinemaCode()) || (this.getCinemaCode() != null
						&& castOther.getCinemaCode() != null && this.getCinemaCode().equals(castOther.getCinemaCode())))
				&& ((this.getCinemaName() == castOther.getCinemaName()) || (this.getCinemaName() != null
						&& castOther.getCinemaName() != null && this.getCinemaName().equals(castOther.getCinemaName())))
				&& ((this.getChannelName() == castOther.getChannelName())
						|| (this.getChannelName() != null && castOther.getChannelName() != null
								&& this.getChannelName().equals(castOther.getChannelName())))
				&& ((this.getGoodName() == castOther.getGoodName()) || (this.getGoodName() != null
						&& castOther.getGoodName() != null && this.getGoodName().equals(castOther.getGoodName())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null && castOther.getUnit() != null
						&& this.getUnit().equals(castOther.getUnit())))
				&& ((this.getAmount() == castOther.getAmount()) || (this.getAmount() != null
						&& castOther.getAmount() != null && this.getAmount().equals(castOther.getAmount())))
				&& ((this.getRealMoney() == castOther.getRealMoney()) || (this.getRealMoney() != null
						&& castOther.getRealMoney() != null && this.getRealMoney().equals(castOther.getRealMoney())))
				&& ((this.getUnitPrice() == castOther.getUnitPrice()) || (this.getUnitPrice() != null
						&& castOther.getUnitPrice() != null && this.getUnitPrice().equals(castOther.getUnitPrice())))
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
		result = 37 * result + (getCinemaCode() == null ? 0 : this.getCinemaCode().hashCode());
		result = 37 * result + (getCinemaName() == null ? 0 : this.getCinemaName().hashCode());
		result = 37 * result + (getChannelName() == null ? 0 : this.getChannelName().hashCode());
		result = 37 * result + (getGoodName() == null ? 0 : this.getGoodName().hashCode());
		result = 37 * result + (getUnit() == null ? 0 : this.getUnit().hashCode());
		result = 37 * result + (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37 * result + (getRealMoney() == null ? 0 : this.getRealMoney().hashCode());
		result = 37 * result + (getUnitPrice() == null ? 0 : this.getUnitPrice().hashCode());
		result = 37 * result + (getCityName() == null ? 0 : this.getCityName().hashCode());
		result = 37 * result + (getProvinceName() == null ? 0 : this.getProvinceName().hashCode());
		result = 37 * result + (getAreaName() == null ? 0 : this.getAreaName().hashCode());
		result = 37 * result + (getEtlTime() == null ? 0 : this.getEtlTime().hashCode());
		result = 37 * result + (getDataSource() == null ? 0 : this.getDataSource().hashCode());
		return result;
	}
}