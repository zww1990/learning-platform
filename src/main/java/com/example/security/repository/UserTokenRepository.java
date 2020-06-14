package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.UserToken;

/**
 * 自动登录持久化
 * 
 * @author home
 */
public interface UserTokenRepository extends JpaRepository<UserToken, String> {
	int deleteByUsername(String username);
}
