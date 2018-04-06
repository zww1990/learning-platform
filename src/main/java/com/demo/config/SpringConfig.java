package com.demo.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan("com.demo")
@MapperScan("com.demo.dao")
public class SpringConfig {
	@Bean
	public ObjectMapper jsonMapper(@Value("${spring.jackson.date-format}") String dateFormat,
			@Value("${spring.jackson.time-zone}") String timeZone) {
		return new ObjectMapper().setDateFormat(new SimpleDateFormat(dateFormat))
				.setTimeZone(TimeZone.getTimeZone(timeZone));
	}
}
