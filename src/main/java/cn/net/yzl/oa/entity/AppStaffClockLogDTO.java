package cn.net.yzl.oa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AppStaffClockLogDTO {
	/** 打卡地址 */
//	private String address;
	/** 打卡日期 */
//	private String clockDate;
	/** 一天打卡的开始时间 */
//	private String clockDayStartTime;
	/** 当前状态(611：正常，612：迟到，614：上班缺卡，616：早退) */
//	private Integer clockStatus;
	/** 末次打卡时间 */
	private String clockTimeMax;
	/** 首次打卡时间 */
	private String clockTimeMin;
	/** 下班打卡状态(611：正常，612：迟到，614：上班缺卡，616：早退) */
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer clockWorkOffStatus;
	private String clockWorkOffStatusName;
	/** 上班打卡状态(611：正常，612：迟到，614：上班缺卡，616：早退) */
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer clockWorkOnStatus;
	private String clockWorkOnStatusName;
	/** 弹性上班开始时间 */
//	private String closeBeginTime;
	/** 弹性下班结束时间 */
//	private String closeEndTime;
	/** 是否空的规则 */
//	private Boolean emptyRule;
	/** 纬度 */
//	private BigDecimal latitude;
	/** 经度 */
//	private BigDecimal longitude;
	/** 是否非职能（只有职能可以打卡） */
//	private Boolean notFunction;
	/** 范围 */
//	private Integer rangeRadius;
	/** 午休时间 */
//	private String restTime;
	/** 是否允许4G打卡 1：允许 0：不允许 */
//	private Integer switch4g;
	/** 上班开始时间 */
//	private String workBeginTime;
	/** 下班开始时间 */
//	private String workEndTime;

	public void setClockWorkOffStatus(Integer clockWorkOffStatus) {
		this.clockWorkOffStatus = clockWorkOffStatus;
		this.clockWorkOffStatusName = ClockWorkStatus.codeToName(clockWorkOffStatus);
	}

	public void setClockWorkOnStatus(Integer clockWorkOnStatus) {
		this.clockWorkOnStatus = clockWorkOnStatus;
		this.clockWorkOnStatusName = ClockWorkStatus.codeToName(clockWorkOnStatus);
	}
}
