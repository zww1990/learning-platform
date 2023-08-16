package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.Menu;

/**
 * 菜单数据持久化
 * 
 * @author home
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
