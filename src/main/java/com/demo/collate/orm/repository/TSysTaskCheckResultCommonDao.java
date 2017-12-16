package com.demo.collate.orm.repository;

import java.util.List;

import com.demo.collate.orm.domain.TSysTaskCheckResultCommon;

public interface TSysTaskCheckResultCommonDao {
	List<TSysTaskCheckResultCommon> select(String table);

	Integer count(String table);

	void dropTable(String table);

	String taskExecCreateTable(String taskNo, String batchNo, String checkDate);

	Integer taskCheckResultCreateTable(String taskNo);
}
