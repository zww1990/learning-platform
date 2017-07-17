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
 * TDmDzIncomeDetail entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_dm_dz_income_detail")
public class TDmDzIncomeDetail implements java.io.Serializable {
	// Fields
	private TDmDzIncomeDetailId id;
	private String paymentName;
	private Date etlDataCycle;

	// Constructors
	/** default constructor */
	public TDmDzIncomeDetail() {}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bookBizDate", column = @Column(name = "BOOK_BIZ_DATE", length = 10)),
			@AttributeOverride(name = "cinemaName", column = @Column(name = "CINEMA_NAME", length = 120)),
			@AttributeOverride(name = "cinemaCode", column = @Column(name = "CINEMA_CODE", length = 10)),
			@AttributeOverride(name = "mobile", column = @Column(name = "MOBILE", length = 20)),
			@AttributeOverride(name = "channelName", column = @Column(name = "CHANNEL_NAME", length = 120)),
			@AttributeOverride(name = "orders", column = @Column(name = "ORDERS", nullable = false)),
			@AttributeOverride(name = "faceMoney", column = @Column(name = "FACE_MONEY", precision = 40, scale = 4)),
			@AttributeOverride(name = "goodMoney", column = @Column(name = "GOOD_MONEY", precision = 30, scale = 4)),
			@AttributeOverride(name = "tspServiceFee", column = @Column(name = "TSP_SERVICE_FEE", precision = 30, scale = 4)),
			@AttributeOverride(name = "totalMoney", column = @Column(name = "TOTAL_MONEY", precision = 31, scale = 4)),
			@AttributeOverride(name = "cityName", column = @Column(name = "CITY_NAME", length = 50)),
			@AttributeOverride(name = "provinceName", column = @Column(name = "PROVINCE_NAME", length = 50)),
			@AttributeOverride(name = "areaName", column = @Column(name = "AREA_NAME", length = 50)),
			@AttributeOverride(name = "etlTime", column = @Column(name = "ETL_TIME", length = 19)),
			@AttributeOverride(name = "dataSource", column = @Column(name = "DATA_SOURCE", length = 50)) })
	public TDmDzIncomeDetailId getId() {
		return this.id;
	}

	public void setId(TDmDzIncomeDetailId id) {
		this.id = id;
	}

	@Column(name = "PAYMENT_NAME", length = 120)
	public String getPaymentName() {
		return this.paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
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