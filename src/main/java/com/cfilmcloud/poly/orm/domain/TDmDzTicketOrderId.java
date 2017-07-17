package com.cfilmcloud.poly.orm.domain;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TDmDzTicketOrderId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Embeddable
public class TDmDzTicketOrderId implements java.io.Serializable {
	// Fields
	private Date bookBizDate;
	private Date bookDate;
	private String orderId;
	private String tspOrderCode;
	private String createTime;
	private String orderState;
	private String filmCode;
	private String filmName;
	private Timestamp showDate;
	private String channelName;
	private String cinemaCode;
	private String cinemaName;
	private String screenId;
	private String screenName;
	private String mobile;
	private String seatInfo;
	private Integer tickets;
	private Double faceMoney;
	private Double ownerFaceMoney;
	private Double tspServiceFee;
	private Double platformServiceFee;
	private Timestamp etlTime;
	private String dataSource;

	// Constructors
	/** default constructor */
	public TDmDzTicketOrderId() {}

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

	@Column(name = "CREATE_TIME", length = 50)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ORDER_STATE", length = 10)
	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	@Column(name = "FILM_CODE", length = 20)
	public String getFilmCode() {
		return this.filmCode;
	}

	public void setFilmCode(String filmCode) {
		this.filmCode = filmCode;
	}

	@Column(name = "FILM_NAME", length = 120)
	public String getFilmName() {
		return this.filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	@Column(name = "SHOW_DATE", length = 19)
	public Timestamp getShowDate() {
		return this.showDate;
	}

	public void setShowDate(Timestamp showDate) {
		this.showDate = showDate;
	}

	@Column(name = "CHANNEL_NAME", length = 120)
	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

	@Column(name = "SCREEN_ID", length = 10)
	public String getScreenId() {
		return this.screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	@Column(name = "SCREEN_NAME", length = 120)
	public String getScreenName() {
		return this.screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@Column(name = "MOBILE", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "SEAT_INFO", length = 120)
	public String getSeatInfo() {
		return this.seatInfo;
	}

	public void setSeatInfo(String seatInfo) {
		this.seatInfo = seatInfo;
	}

	@Column(name = "TICKETS")
	public Integer getTickets() {
		return this.tickets;
	}

	public void setTickets(Integer tickets) {
		this.tickets = tickets;
	}

	@Column(name = "FACE_MONEY", precision = 8, scale = 4)
	public Double getFaceMoney() {
		return this.faceMoney;
	}

	public void setFaceMoney(Double faceMoney) {
		this.faceMoney = faceMoney;
	}

	@Column(name = "OWNER_FACE_MONEY", precision = 8, scale = 4)
	public Double getOwnerFaceMoney() {
		return this.ownerFaceMoney;
	}

	public void setOwnerFaceMoney(Double ownerFaceMoney) {
		this.ownerFaceMoney = ownerFaceMoney;
	}

	@Column(name = "TSP_SERVICE_FEE", precision = 8, scale = 4)
	public Double getTspServiceFee() {
		return this.tspServiceFee;
	}

	public void setTspServiceFee(Double tspServiceFee) {
		this.tspServiceFee = tspServiceFee;
	}

	@Column(name = "PLATFORM_SERVICE_FEE", precision = 8, scale = 4)
	public Double getPlatformServiceFee() {
		return this.platformServiceFee;
	}

	public void setPlatformServiceFee(Double platformServiceFee) {
		this.platformServiceFee = platformServiceFee;
	}

	@Column(name = "ETL_TIME", length = 19)
	public Timestamp getEtlTime() {
		return this.etlTime;
	}

	public void setEtlTime(Timestamp etlTime) {
		this.etlTime = etlTime;
	}

	@Column(name = "DATA_SOURCE", nullable = false, length = 50)
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
		if (!(other instanceof TDmDzTicketOrderId))
			return false;
		TDmDzTicketOrderId castOther = (TDmDzTicketOrderId) other;
		return ((this.getBookBizDate() == castOther.getBookBizDate()) || (this.getBookBizDate() != null
				&& castOther.getBookBizDate() != null && this.getBookBizDate().equals(castOther.getBookBizDate())))
				&& ((this.getBookDate() == castOther.getBookDate()) || (this.getBookDate() != null
						&& castOther.getBookDate() != null && this.getBookDate().equals(castOther.getBookDate())))
				&& ((this.getOrderId() == castOther.getOrderId()) || (this.getOrderId() != null
						&& castOther.getOrderId() != null && this.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getTspOrderCode() == castOther.getTspOrderCode())
						|| (this.getTspOrderCode() != null && castOther.getTspOrderCode() != null
								&& this.getTspOrderCode().equals(castOther.getTspOrderCode())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this.getCreateTime() != null
						&& castOther.getCreateTime() != null && this.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getOrderState() == castOther.getOrderState()) || (this.getOrderState() != null
						&& castOther.getOrderState() != null && this.getOrderState().equals(castOther.getOrderState())))
				&& ((this.getFilmCode() == castOther.getFilmCode()) || (this.getFilmCode() != null
						&& castOther.getFilmCode() != null && this.getFilmCode().equals(castOther.getFilmCode())))
				&& ((this.getFilmName() == castOther.getFilmName()) || (this.getFilmName() != null
						&& castOther.getFilmName() != null && this.getFilmName().equals(castOther.getFilmName())))
				&& ((this.getShowDate() == castOther.getShowDate()) || (this.getShowDate() != null
						&& castOther.getShowDate() != null && this.getShowDate().equals(castOther.getShowDate())))
				&& ((this.getChannelName() == castOther.getChannelName())
						|| (this.getChannelName() != null && castOther.getChannelName() != null
								&& this.getChannelName().equals(castOther.getChannelName())))
				&& ((this.getCinemaCode() == castOther.getCinemaCode()) || (this.getCinemaCode() != null
						&& castOther.getCinemaCode() != null && this.getCinemaCode().equals(castOther.getCinemaCode())))
				&& ((this.getCinemaName() == castOther.getCinemaName()) || (this.getCinemaName() != null
						&& castOther.getCinemaName() != null && this.getCinemaName().equals(castOther.getCinemaName())))
				&& ((this.getScreenId() == castOther.getScreenId()) || (this.getScreenId() != null
						&& castOther.getScreenId() != null && this.getScreenId().equals(castOther.getScreenId())))
				&& ((this.getScreenName() == castOther.getScreenName()) || (this.getScreenName() != null
						&& castOther.getScreenName() != null && this.getScreenName().equals(castOther.getScreenName())))
				&& ((this.getMobile() == castOther.getMobile()) || (this.getMobile() != null
						&& castOther.getMobile() != null && this.getMobile().equals(castOther.getMobile())))
				&& ((this.getSeatInfo() == castOther.getSeatInfo()) || (this.getSeatInfo() != null
						&& castOther.getSeatInfo() != null && this.getSeatInfo().equals(castOther.getSeatInfo())))
				&& ((this.getTickets() == castOther.getTickets()) || (this.getTickets() != null
						&& castOther.getTickets() != null && this.getTickets().equals(castOther.getTickets())))
				&& ((this.getFaceMoney() == castOther.getFaceMoney()) || (this.getFaceMoney() != null
						&& castOther.getFaceMoney() != null && this.getFaceMoney().equals(castOther.getFaceMoney())))
				&& ((this.getOwnerFaceMoney() == castOther.getOwnerFaceMoney())
						|| (this.getOwnerFaceMoney() != null && castOther.getOwnerFaceMoney() != null
								&& this.getOwnerFaceMoney().equals(castOther.getOwnerFaceMoney())))
				&& ((this.getTspServiceFee() == castOther.getTspServiceFee())
						|| (this.getTspServiceFee() != null && castOther.getTspServiceFee() != null
								&& this.getTspServiceFee().equals(castOther.getTspServiceFee())))
				&& ((this.getPlatformServiceFee() == castOther.getPlatformServiceFee())
						|| (this.getPlatformServiceFee() != null && castOther.getPlatformServiceFee() != null
								&& this.getPlatformServiceFee().equals(castOther.getPlatformServiceFee())))
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
		result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode());
		result = 37 * result + (getOrderState() == null ? 0 : this.getOrderState().hashCode());
		result = 37 * result + (getFilmCode() == null ? 0 : this.getFilmCode().hashCode());
		result = 37 * result + (getFilmName() == null ? 0 : this.getFilmName().hashCode());
		result = 37 * result + (getShowDate() == null ? 0 : this.getShowDate().hashCode());
		result = 37 * result + (getChannelName() == null ? 0 : this.getChannelName().hashCode());
		result = 37 * result + (getCinemaCode() == null ? 0 : this.getCinemaCode().hashCode());
		result = 37 * result + (getCinemaName() == null ? 0 : this.getCinemaName().hashCode());
		result = 37 * result + (getScreenId() == null ? 0 : this.getScreenId().hashCode());
		result = 37 * result + (getScreenName() == null ? 0 : this.getScreenName().hashCode());
		result = 37 * result + (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result + (getSeatInfo() == null ? 0 : this.getSeatInfo().hashCode());
		result = 37 * result + (getTickets() == null ? 0 : this.getTickets().hashCode());
		result = 37 * result + (getFaceMoney() == null ? 0 : this.getFaceMoney().hashCode());
		result = 37 * result + (getOwnerFaceMoney() == null ? 0 : this.getOwnerFaceMoney().hashCode());
		result = 37 * result + (getTspServiceFee() == null ? 0 : this.getTspServiceFee().hashCode());
		result = 37 * result + (getPlatformServiceFee() == null ? 0 : this.getPlatformServiceFee().hashCode());
		result = 37 * result + (getEtlTime() == null ? 0 : this.getEtlTime().hashCode());
		result = 37 * result + (getDataSource() == null ? 0 : this.getDataSource().hashCode());
		return result;
	}
}