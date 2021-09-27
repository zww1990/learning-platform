package cn.net.yzl.oa.entity;

import java.util.Arrays;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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

	public static String codeToName(Integer code) {
		return Optional.ofNullable(code)// NOSONAR
				.map(m -> Arrays.stream(values())// NOSONAR
						.filter(p -> p.code.equals(m))// NOSONAR
						.findFirst()// NOSONAR
						.map(ClockWorkStatus::getName)// NOSONAR
						.orElseGet(String::new))// NOSONAR
				.orElseGet(String::new);
	}
}
