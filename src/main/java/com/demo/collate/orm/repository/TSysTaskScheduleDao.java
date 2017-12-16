package com.demo.collate.orm.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.collate.orm.domain.TSysTaskSchedule;

public interface TSysTaskScheduleDao
		extends JpaRepository<TSysTaskSchedule, Integer>, JpaSpecificationExecutor<TSysTaskSchedule> {
	@Modifying
	@Query("delete from TSysTaskSchedule where rowId not in (:rowIds) and taskNo = :taskNo")
	int deleteByRowIdAndTaskNo(@Param("rowIds") Collection<Integer> rowIds, @Param("taskNo") String taskNo);
}
