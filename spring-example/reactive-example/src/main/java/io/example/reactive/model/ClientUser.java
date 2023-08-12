package io.example.reactive.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;

/**
 * 用户模型
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午4:10:52
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class ClientUser {
	/** 序列 */
	@Id
	private Integer sequence;
	/** 用户唯一标识 */
	private String userId;
	/** 姓名 */
	private String username;
	/** 手机号 */
	private String phoneNumber;
	/** 性别 */
	private Integer gender;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ReadOnlyProperty
	private LocalDateTime createdDate;
	/** 最后修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ReadOnlyProperty
	private LocalDateTime modifiedDate;
}
