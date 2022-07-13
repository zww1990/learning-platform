package com.example.springreactive.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * ClientUser
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
	@Id
	private Integer sequence;
	private String userId;
	private String username;
	private String phoneNumber;
	private Integer gender;
	@ReadOnlyProperty
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createdDate;
	@ReadOnlyProperty
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime modifiedDate;
}
