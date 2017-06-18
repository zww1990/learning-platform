package com.cfilmcloud.collate.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cfilmcloud.collate.orm.domain.TSysTaskConfig;

public interface TSysTaskConfigDao
		extends JpaRepository<TSysTaskConfig, Integer>, JpaSpecificationExecutor<TSysTaskConfig> {
	@Query("from TSysTaskConfig where taskNo = :taskNo")
	TSysTaskConfig findByTaskNo(@Param("taskNo") String taskNo);
}
