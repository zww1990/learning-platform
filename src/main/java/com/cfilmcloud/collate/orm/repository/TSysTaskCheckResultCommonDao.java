package com.cfilmcloud.collate.orm.repository;

import java.util.List;

import com.cfilmcloud.collate.orm.domain.TSysTaskCheckResultCommon;

public interface TSysTaskCheckResultCommonDao {
	List<TSysTaskCheckResultCommon> select(String table);

	Integer count(String table);
}
