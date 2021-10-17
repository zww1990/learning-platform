package cn.net.yzl.oa.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AppStaffClockLog {
	private Integer id;
	private String staff_no;
	private String clock_time;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private String address;
	private Integer clock_type;
	private String create_time;
}
