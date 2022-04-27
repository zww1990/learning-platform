package com.example.springreactive.model;

import org.springframework.data.annotation.Id;

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
	private String userId;
	private String username;
	private String phoneNumber;
	private Integer gender;
}
