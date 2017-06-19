package com.cfilmcloud.collate.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cfilmcloud.collate.orm.domain.TSysDsConfigItem;

public interface TSysDsConfigItemDao
		extends JpaRepository<TSysDsConfigItem, Integer>, JpaSpecificationExecutor<TSysDsConfigItem> {
	@Query("from TSysDsConfigItem where columnName in (select sourceColumnName from TSysTaskConfigItemRel where sourceDataSourceId = :srcDataSrcId and taskNo = :taskNo) and dataSourceId = :srcDataSrcId")
	List<TSysDsConfigItem> findADsConfigItem(@Param("srcDataSrcId") String srcDataSrcId,
			@Param("taskNo") String taskNo);

	@Query("from TSysDsConfigItem where columnName in (select targetColumnName from TSysTaskConfigItemRel where targetDataSourceId = :tarDataSrcId and taskNo = :taskNo) and dataSourceId = :tarDataSrcId")
	List<TSysDsConfigItem> findBDsConfigItem(@Param("tarDataSrcId") String tarDataSrcId,
			@Param("taskNo") String taskNo);
}
