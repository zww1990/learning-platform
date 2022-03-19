package cn.net.yzl.oa.entity;

import java.math.BigDecimal;

public class AppStaffClockLog {
	private Integer id;
	private String staff_no;
	private String clock_time;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private String address;
	private Integer clock_type;
	private String create_time;

	public Integer getId() {
		return id;
	}

	public String getStaff_no() {
		return staff_no;
	}

	public String getClock_time() {
		return clock_time;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public String getAddress() {
		return address;
	}

	public Integer getClock_type() {
		return clock_type;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStaff_no(String staff_no) {
		this.staff_no = staff_no;
	}

	public void setClock_time(String clock_time) {
		this.clock_time = clock_time;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setClock_type(Integer clock_type) {
		this.clock_type = clock_type;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return String.format(
				"AppStaffClockLog [id=%s, staff_no=%s, clock_time=%s, longitude=%s, latitude=%s, address=%s, clock_type=%s, create_time=%s]",
				id, staff_no, clock_time, longitude, latitude, address, clock_type, create_time);
	}
}
