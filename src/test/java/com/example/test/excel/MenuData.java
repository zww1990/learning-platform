package com.example.test.excel;

import java.util.List;

/**
 * MenuData
 * 
 * @author zww1990@foxmail.com
 * @since 2022年1月8日,下午4:51:46
 */
public class MenuData {
	private String menuName;
	private String component;
	private List<MenuData> childrens;

	public String getMenuName() {
		return menuName;
	}

	public String getComponent() {
		return component;
	}

	public List<MenuData> getChildrens() {
		return childrens;
	}

	public MenuData setMenuName(String menuName) {
		this.menuName = menuName;
		return this;
	}

	public MenuData setComponent(String component) {
		this.component = component;
		return this;
	}

	public MenuData setChildrens(List<MenuData> childrens) {
		this.childrens = childrens;
		return this;
	}

	@Override
	public String toString() {
		return String.format("MenuData [menuName=%s, component=%s, childrens=%s]", menuName, component, childrens);
	}
}
