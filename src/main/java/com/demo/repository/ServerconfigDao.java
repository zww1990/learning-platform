package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.domain.Serverconfig;

public interface ServerconfigDao extends JpaRepository<Serverconfig, Long> {
}
