package cn.net.yzl.oa.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.net.yzl.oa.util.AESUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author
 * @Date 2021/4/8
 * @Description
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
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

}
