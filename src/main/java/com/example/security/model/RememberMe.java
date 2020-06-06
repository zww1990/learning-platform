package com.example.security.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 自动登录表
 * 
 * @author home
 */
@Entity
@Table(name = "t_remember_me")
public class RememberMe {
	@Id
	private String series;
	private String username;
	private String token;
	private Date lastUsed;

	public RememberMe() {
		super();
	}

	public RememberMe(String series, String username, String token, Date lastUsed) {
		super();
		this.series = series;
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	public String getSeries() {
		return series;
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
}
