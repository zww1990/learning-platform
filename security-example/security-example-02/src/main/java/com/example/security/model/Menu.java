package com.example.security.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 菜单
 * 
 * @author home
 */
@Entity
@Table(name = "t_menu")
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class Menu implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer menuId;
	private String menuName;
	private String patternUrl;
	private boolean enabled;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_menu_id")
	private Menu parentMenu;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_menu_authority", //
			joinColumns = @JoinColumn(name = "menu_id"), //
			inverseJoinColumns = @JoinColumn(name = "auth_id"))
	private List<Authority> authorities;

	public Menu() {
		super();
	}

	public Menu(String menuName, String patternUrl, boolean enabled, Menu parentMenu, List<Authority> authorities) {
		super();
		this.menuName = menuName;
		this.patternUrl = patternUrl;
		this.enabled = enabled;
		this.parentMenu = parentMenu;
		this.authorities = authorities;
	}

}
