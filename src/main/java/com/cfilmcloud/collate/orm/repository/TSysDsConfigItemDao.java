package com.cfilmcloud.collate.orm.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cfilmcloud.collate.orm.domain.TSysDsConfigItem;

public interface TSysDsConfigItemDao
		extends JpaRepository<TSysDsConfigItem, Integer>, JpaSpecificationExecutor<TSysDsConfigItem> {
	@Query("from TSysDsConfigItem where dataSourceId = :srcDataSrcId")
	List<TSysDsConfigItem> findADsConfigItem(@Param("srcDataSrcId") String srcDataSrcId);

	@Query("from TSysDsConfigItem where dataSourceId = :tarDataSrcId")
	List<TSysDsConfigItem> findBDsConfigItem(@Param("tarDataSrcId") String tarDataSrcId);
}
