package com.example.security.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自动登录表
 * 
 * @author home
 */
@Entity
@Table(name = "t_user_token")
@Getter
@Setter
@ToString
public class UserToken {
	@Id
	private String series;
	private String username;
	private String token;
	private Date lastUsed;

	public UserToken() {
		super();
	}

	public UserToken(String series, String username, String token, Date lastUsed) {
		super();
		this.series = series;
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}

}
