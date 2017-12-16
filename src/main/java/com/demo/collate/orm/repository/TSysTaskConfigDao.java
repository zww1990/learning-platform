package com.demo.collate.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.collate.orm.domain.TSysTaskConfig;

public interface TSysTaskConfigDao
		extends JpaRepository<TSysTaskConfig, Integer>, JpaSpecificationExecutor<TSysTaskConfig> {
	@Query("from TSysTaskConfig where taskNo = :taskNo")
	TSysTaskConfig findByTaskNo(@Param("taskNo") String taskNo);

	@Query(value = "SELECT concat( 'T1', lpad(( CASE WHEN MAX(t.ROW_ID) IS NULL THEN 0 ELSE MAX(t.ROW_ID) END ) + 1, 6, 0 )) FROM t_sys_task_config t;", nativeQuery = true)
	String generateTaskNo();

	@Query("select DISTINCT taskNo from TSysTaskConfig")
	List<String> findTaskNo();
}
