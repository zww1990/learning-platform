package com.demo.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan("com.demo")
@MapperScan("com.demo.dao")
public class SpringConfig {
	@Bean
	public ObjectMapper jsonMapper(@Value("${spring.jackson.date-format}") String dateFormat,
			@Value("${spring.jackson.time-zone}") String timeZone) {
		return new ObjectMapper().setDateFormat(new SimpleDateFormat(dateFormat))// 设置日期格式
				.setTimeZone(TimeZone.getTimeZone(timeZone))// 设置时区
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)// 设置未知属性
				.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)// 设置无效的子类型
		;
	}
}
