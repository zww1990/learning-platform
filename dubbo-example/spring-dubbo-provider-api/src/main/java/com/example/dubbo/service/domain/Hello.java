package com.example.dubbo.service.domain;

import java.io.Serializable;
import java.time.LocalDate;

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
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Hello implements Serializable {
	/** 编号 */
	private long id;
	/** 姓名 */
	private String name;
	/** 年龄 */
	private int age;
	/** 生日 */
	private LocalDate birthday;
}
