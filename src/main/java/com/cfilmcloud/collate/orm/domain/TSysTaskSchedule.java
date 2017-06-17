package com.cfilmcloud.collate.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * TSysTaskSchedule entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_task_schedule")
public class TSysTaskSchedule implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String taskNo;
	private String what;
	private String intervals;
	private String bdate;
	private String edate;
	private Date thisTime;
	private Date nextTime;
	private Date lastTime;
	private Integer timeCost;
	private String noticeType;
	private String noticeTel;
	private Integer isSleeping;
	private Integer isEnable;
	private Integer isDelete;
	private Date deleteTime;
	private String remark;
	private Date createTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public TSysTaskSchedule() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "row_id", unique = true, nullable = false)

	public Integer getRowId() {
		return this.rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	@Column(name = "task_no", length = 8)
	public String getTaskNo() {
		return this.taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	@Column(name = "what", length = 600)
	public String getWhat() {
		return this.what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	@Column(name = "intervals", length = 80)
	public String getIntervals() {
		return this.intervals;
	}

	public void setIntervals(String intervals) {
		this.intervals = intervals;
	}

	@Column(name = "bdate", length = 80)
	public String getBdate() {
		return this.bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	@Column(name = "edate", length = 80)
	public String getEdate() {
		return this.edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	@Column(name = "this_time", length = 19)
	public Date getThisTime() {
		return this.thisTime;
	}

	public void setThisTime(Date thisTime) {
		this.thisTime = thisTime;
	}

	@Column(name = "next_time", length = 19)
	public Date getNextTime() {
		return this.nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	@Column(name = "last_time", length = 19)
	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "time_cost")
	public Integer getTimeCost() {
		return this.timeCost;
	}

	public void setTimeCost(Integer timeCost) {
		this.timeCost = timeCost;
	}

	@Column(name = "notice_type", length = 50)
	public String getNoticeType() {
		return this.noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	@Column(name = "notice_tel", length = 600)
	public String getNoticeTel() {
		return this.noticeTel;
	}

	public void setNoticeTel(String noticeTel) {
		this.noticeTel = noticeTel;
	}

	@Column(name = "is_sleeping")
	public Integer getIsSleeping() {
		return this.isSleeping;
	}

	public void setIsSleeping(Integer isSleeping) {
		this.isSleeping = isSleeping;
	}

	@Column(name = "is_enable")
	public Integer getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "delete_time", length = 19)
	public Date getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "remark", length = 120)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "create_time", length = 19, updatable = false)
	@CreationTimestamp
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 19)
	@UpdateTimestamp
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}