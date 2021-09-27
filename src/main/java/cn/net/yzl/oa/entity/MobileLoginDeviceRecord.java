package cn.net.yzl.oa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class MobileLoginDeviceRecord {
	private String bindDeviceId;
	private String browserName;
	private String browserVersion;
	private String deviceId;
	private String model;
	private String pushId;
	private String resolution;
	private String staffNo;
	private String systemName;
	private String systemVersion;
}
