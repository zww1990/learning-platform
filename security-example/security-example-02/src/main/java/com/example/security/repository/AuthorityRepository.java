package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.Authority;

/**
 * 权限数据持久化
 * 
 * @author home
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
