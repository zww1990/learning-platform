package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.domain.Appnamespace;

public interface AppnamespaceDao extends JpaRepository<Appnamespace, Long> {
}
