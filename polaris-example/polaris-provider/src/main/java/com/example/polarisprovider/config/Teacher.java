package com.example.polarisprovider.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义属性配置
 * 
 * @author zhang weiwei
 * @since 2023年8月4日,下午7:37:07
 */
@Configuration
@ConfigurationProperties(prefix = "teacher")
@Getter
@Setter
@ToString
public class Teacher {
	/** 姓名 */
	private String name;
	/** 年龄 */
	private int age;
}
