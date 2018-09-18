package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.domain.App;

public interface AppDao extends JpaRepository<App, Long> {
}
