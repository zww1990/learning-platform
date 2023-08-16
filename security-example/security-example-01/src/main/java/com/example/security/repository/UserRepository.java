package com.example.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.User;

/**
 * 用户数据持久化
 * 
 * @author home
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByUsername(String username);
}
