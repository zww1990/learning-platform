package cn.net.yzl.oa.entity.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * app_staff_clock_log
 * 
 * @author
 */
@Getter
@Setter
@ToString
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

}
