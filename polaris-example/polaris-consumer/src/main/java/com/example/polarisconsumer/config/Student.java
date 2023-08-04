package com.example.polarisconsumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义属性配置
 * 
 * @author zhang weiwei
 * @since 2023年8月4日,下午7:43:14
 */
@Configuration
@ConfigurationProperties(prefix = "student")
@Getter
@Setter
@ToString
public class Student {
	/** 姓名 */
	private String name;
	/** 年龄 */
	private int age;
}
