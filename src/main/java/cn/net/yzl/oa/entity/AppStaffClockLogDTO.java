package cn.net.yzl.oa.entity;

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

	public String getClockTimeMax() {
		return clockTimeMax;
	}

	public String getClockTimeMin() {
		return clockTimeMin;
	}

	public Integer getClockWorkOffStatus() {
		return clockWorkOffStatus;
	}

	public String getClockWorkOffStatusName() {
		return clockWorkOffStatusName;
	}

	public Integer getClockWorkOnStatus() {
		return clockWorkOnStatus;
	}

	public String getClockWorkOnStatusName() {
		return clockWorkOnStatusName;
	}

	public void setClockTimeMax(String clockTimeMax) {
		this.clockTimeMax = clockTimeMax;
	}

	public void setClockTimeMin(String clockTimeMin) {
		this.clockTimeMin = clockTimeMin;
	}

	public void setClockWorkOffStatusName(String clockWorkOffStatusName) {
		this.clockWorkOffStatusName = clockWorkOffStatusName;
	}

	public void setClockWorkOnStatusName(String clockWorkOnStatusName) {
		this.clockWorkOnStatusName = clockWorkOnStatusName;
	}

	@Override
	public String toString() {
		return String.format(
				"AppStaffClockLogDTO [clockTimeMax=%s, clockTimeMin=%s, clockWorkOffStatus=%s, clockWorkOffStatusName=%s, clockWorkOnStatus=%s, clockWorkOnStatusName=%s]",
				clockTimeMax, clockTimeMin, clockWorkOffStatus, clockWorkOffStatusName, clockWorkOnStatus,
				clockWorkOnStatusName);
	}
}
