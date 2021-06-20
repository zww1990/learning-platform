package com.example.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限
 * 
 * @author home
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_authority")
public class Authority implements GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authId;
	private String authName;
	private String authority;

	public Authority() {
		super();
	}

	public Authority(String authName, String authority) {
		super();
		this.authName = authName;
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

	public Integer getAuthId() {
		return authId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
