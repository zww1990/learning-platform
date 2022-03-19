package cn.net.yzl.oa.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.net.yzl.oa.util.AESUtil;

/**
 * @Author
 * @Date 2021/4/8
 * @Description
 */
public class AppStaffClockVO {

	private String staffNo;

	private BigDecimal longitude;

	private BigDecimal latitude;

	private String fileUrl;

	private String fileName;

	private String remark;

	@JsonFormat(pattern = AESUtil.FORMAT, timezone = AESUtil.TIMEZONE)
	private Date clockTime;
	private String address;

	public String getStaffNo() {
		return staffNo;
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

	public String getRemark() {
		return remark;
	}

	public Date getClockTime() {
		return clockTime;
	}

	public String getAddress() {
		return address;
	}

	public AppStaffClockVO setStaffNo(String staffNo) {
		this.staffNo = staffNo;
		return this;
	}

	public AppStaffClockVO setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
		return this;
	}

	public AppStaffClockVO setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
		return this;
	}

	public AppStaffClockVO setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
		return this;
	}

	public AppStaffClockVO setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public AppStaffClockVO setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	public AppStaffClockVO setClockTime(Date clockTime) {
		this.clockTime = clockTime;
		return this;
	}

	public AppStaffClockVO setAddress(String address) {
		this.address = address;
		return this;
	}

	@Override
	public String toString() {
		return String.format(
				"AppStaffClockVO [staffNo=%s, longitude=%s, latitude=%s, fileUrl=%s, fileName=%s, remark=%s, clockTime=%s, address=%s]",
				staffNo, longitude, latitude, fileUrl, fileName, remark, clockTime, address);
	}

}
