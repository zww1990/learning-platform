package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.RememberMe;

/**
 * 自动登录持久化
 * 
 * @author home
 */
public interface RememberMeRepository extends JpaRepository<RememberMe, String> {
	int deleteByUsername(String username);
}
