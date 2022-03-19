package cn.net.yzl.oa.entity.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * app_staff_clock_log
 * 
 * @author
 */
public class AppStaffClockLogPo {
	private Integer id;

	/**
	 * 员工编号
	 */
	private String staffNo;

	/**
	 * 打卡时间
	 */
	private Date clockTime;

	/**
	 * 上班弹性开始时间
	 */
	private String workBeginTime;

	/**
	 * 上班弹性结束时间
	 */
	private String workEndTime;

	/**
	 * 下班弹性开始时间
	 */
	private String closeBeginTime;

	/**
	 * 下班弹性结束时间
	 */
	private String closeEndTime;

	/**
	 * 经度
	 */
	private BigDecimal longitude;

	/**
	 * 纬度
	 */
	private BigDecimal latitude;

	/**
	 * 文件地址
	 */
	private String fileUrl;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 打卡类型（0：OA打卡 1：审批补卡）
	 */
	private Integer clockType;

	/**
	 * 打卡异常备注
	 */
	private String remark;

	private String address;

	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 午休开始时间
	 */
	private String restTime;

	/**
	 * 记录考勤时对应的规则id
	 */
	private Integer departAttendRuleId;

	public Integer getId() {
		return id;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public Date getClockTime() {
		return clockTime;
	}

	public String getWorkBeginTime() {
		return workBeginTime;
	}

	public String getWorkEndTime() {
		return workEndTime;
	}

	public String getCloseBeginTime() {
		return closeBeginTime;
	}

	public String getCloseEndTime() {
		return closeEndTime;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public Integer getClockType() {
		return clockType;
	}

	public String getRemark() {
		return remark;
	}

	public String getAddress() {
		return address;
	}

	public String getCreator() {
		return creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getRestTime() {
		return restTime;
	}

	public Integer getDepartAttendRuleId() {
		return departAttendRuleId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public void setClockTime(Date clockTime) {
		this.clockTime = clockTime;
	}

	public void setWorkBeginTime(String workBeginTime) {
		this.workBeginTime = workBeginTime;
	}

	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}

	public void setCloseBeginTime(String closeBeginTime) {
		this.closeBeginTime = closeBeginTime;
	}

	public void setCloseEndTime(String closeEndTime) {
		this.closeEndTime = closeEndTime;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setClockType(Integer clockType) {
		this.clockType = clockType;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setRestTime(String restTime) {
		this.restTime = restTime;
	}

	public void setDepartAttendRuleId(Integer departAttendRuleId) {
		this.departAttendRuleId = departAttendRuleId;
	}

	@Override
	public String toString() {
		return String.format(
				"AppStaffClockLogPo [id=%s, staffNo=%s, clockTime=%s, workBeginTime=%s, workEndTime=%s, closeBeginTime=%s, closeEndTime=%s, longitude=%s, latitude=%s, fileUrl=%s, fileName=%s, clockType=%s, remark=%s, address=%s, creator=%s, createTime=%s, restTime=%s, departAttendRuleId=%s]",
				id, staffNo, clockTime, workBeginTime, workEndTime, closeBeginTime, closeEndTime, longitude, latitude,
				fileUrl, fileName, clockType, remark, address, creator, createTime, restTime, departAttendRuleId);
	}

}
