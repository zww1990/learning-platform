package io.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ApplicationProperties
 * 
 * @author zhang weiwei
 * @since 2022年8月13日,下午8:58:14
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
public class ApplicationProperties {
	private String readFolder;
	private String writeFolder;
	private String dateTimePattern;
}
