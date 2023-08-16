package cn.net.yzl.oa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AppStaffClockLogDTO {
	/** 末次打卡时间 */
	private String clockTimeMax;
	/** 首次打卡时间 */
	private String clockTimeMin;
	/** 下班打卡状态(611：正常，612：迟到，614：上班缺卡，616：早退) */
	private Integer clockWorkOffStatus;
	private String clockWorkOffStatusName;
	/** 上班打卡状态(611：正常，612：迟到，614：上班缺卡，616：早退) */
	private Integer clockWorkOnStatus;
	private String clockWorkOnStatusName;

	public void setClockWorkOffStatus(Integer clockWorkOffStatus) {
		this.clockWorkOffStatus = clockWorkOffStatus;
		this.clockWorkOffStatusName = ClockWorkStatus.codeToName(clockWorkOffStatus);
	}

	public void setClockWorkOnStatus(Integer clockWorkOnStatus) {
		this.clockWorkOnStatus = clockWorkOnStatus;
		this.clockWorkOnStatusName = ClockWorkStatus.codeToName(clockWorkOnStatus);
	}
}
