package com.example.test.excel;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * MenuData
 * 
 * @author zww1990@foxmail.com
 * @since 2022年1月8日,下午4:51:46
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class MenuData {
	private Integer menuId;
	private String menuName;
	private Integer parentId;
	private String component;
	private List<MenuData> childrens;
}
