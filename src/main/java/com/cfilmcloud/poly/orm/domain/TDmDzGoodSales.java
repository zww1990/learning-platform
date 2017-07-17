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
 * TDmDzGoodSales entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_dm_dz_good_sales")
public class TDmDzGoodSales implements java.io.Serializable {
	// Fields
	private TDmDzGoodSalesId id;
	private String paymentName;
	private String goodType;
	private Date etlDataCycle;

	// Constructors
	/** default constructor */
	public TDmDzGoodSales() {}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bookBizDate", column = @Column(name = "BOOK_BIZ_DATE", length = 10)),
			@AttributeOverride(name = "cinemaCode", column = @Column(name = "CINEMA_CODE", length = 10)),
			@AttributeOverride(name = "cinemaName", column = @Column(name = "CINEMA_NAME", length = 120)),
			@AttributeOverride(name = "channelName", column = @Column(name = "CHANNEL_NAME", length = 120)),
			@AttributeOverride(name = "goodName", column = @Column(name = "GOOD_NAME", length = 120)),
			@AttributeOverride(name = "unit", column = @Column(name = "UNIT", length = 80)),
			@AttributeOverride(name = "amount", column = @Column(name = "AMOUNT", precision = 32, scale = 4)),
			@AttributeOverride(name = "realMoney", column = @Column(name = "REAL_MONEY", precision = 30, scale = 4)),
			@AttributeOverride(name = "unitPrice", column = @Column(name = "UNIT_PRICE", precision = 8, scale = 4)),
			@AttributeOverride(name = "cityName", column = @Column(name = "CITY_NAME", length = 50)),
			@AttributeOverride(name = "provinceName", column = @Column(name = "PROVINCE_NAME", length = 50)),
			@AttributeOverride(name = "areaName", column = @Column(name = "AREA_NAME", length = 50)),
			@AttributeOverride(name = "etlTime", column = @Column(name = "ETL_TIME", length = 19)),
			@AttributeOverride(name = "dataSource", column = @Column(name = "DATA_SOURCE", length = 50)) })
	public TDmDzGoodSalesId getId() {
		return this.id;
	}

	public void setId(TDmDzGoodSalesId id) {
		this.id = id;
	}

	@Column(name = "PAYMENT_NAME", length = 120)
	public String getPaymentName() {
		return this.paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	@Column(name = "GOOD_TYPE", length = 2)
	public String getGoodType() {
		return this.goodType;
	}

	public void setGoodType(String goodType) {
		this.goodType = goodType;
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