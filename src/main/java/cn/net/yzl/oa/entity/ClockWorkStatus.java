package cn.net.yzl.oa.entity;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public enum ClockWorkStatus {
	/** 正常 */
	STATUS_611(611, "正常"),
	/** 迟到 */
	STATUS_612(612, "迟到"),
	/** 上班缺卡 */
	STATUS_614(614, "上班缺卡"),
	/** 早退 */
	STATUS_616(616, "早退");

	private Integer code;
	private String name;

	private ClockWorkStatus(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static String codeToName(Integer code) {
		return Optional.ofNullable(code)
				.map(m -> Arrays.stream(values())
						.collect(Collectors.toMap(ClockWorkStatus::getCode, ClockWorkStatus::getName))
						.getOrDefault(code, code.toString()))
				.orElse(null);
	}
}
