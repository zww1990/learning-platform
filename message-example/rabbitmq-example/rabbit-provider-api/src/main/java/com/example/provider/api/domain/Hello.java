package com.example.provider.api.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 示例模型
 * 
 * @author zhang weiwei
 * @since 2023年8月2日,上午11:30:36
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Hello {
	/** 编号 */
	private long id;
	/** 姓名 */
	private String name;
	/** 年龄 */
	private int age;
	/** 生日 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	/** 发生时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime sendtime;
}
