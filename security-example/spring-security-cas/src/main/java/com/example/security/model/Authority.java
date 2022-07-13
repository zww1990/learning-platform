package com.example.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限
 * 
 * @author home
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_authority")
@Getter
@Setter
@ToString
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

}
