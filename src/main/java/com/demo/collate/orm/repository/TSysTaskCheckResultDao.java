package com.demo.collate.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.collate.orm.domain.TSysTaskCheckResult;

public interface TSysTaskCheckResultDao
		extends JpaRepository<TSysTaskCheckResult, Integer>, JpaSpecificationExecutor<TSysTaskCheckResult> {

}
