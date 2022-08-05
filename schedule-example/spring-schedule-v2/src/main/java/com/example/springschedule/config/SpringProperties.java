package com.example.springschedule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Spring Properties
 * 
 * @author zhang weiwei
 * @since 2022年8月5日,下午8:16:54
 */
@ConfigurationProperties(prefix = "cron")
@Configuration
@Getter
@Setter
@ToString
public class SpringProperties {
	private String expression;
}
