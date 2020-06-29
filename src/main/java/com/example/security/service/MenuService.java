package com.example.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.security.model.Menu;
import com.example.security.repository.MenuRepository;

/**
 * 菜单服务
 * 
 * @author home
 */
public interface MenuService {
	/**
	 * @return 查询所有菜单项，并按主键升序。
	 */
	List<Menu> queryMenus();

	/**
	 * 菜单服务实现
	 * 
	 * @author home
	 */
	@Service
	class MenuServiceImpl implements MenuService {
		@Resource
		private MenuRepository menuRepository;

		@Override
		public List<Menu> queryMenus() {
			return this.menuRepository.findAll(Sort.by(Order.asc("menuId")));
		}
	}
}
