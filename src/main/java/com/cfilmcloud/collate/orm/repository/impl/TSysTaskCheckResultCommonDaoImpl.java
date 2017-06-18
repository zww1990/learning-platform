package com.cfilmcloud.collate.orm.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cfilmcloud.collate.orm.domain.TSysTaskCheckResultCommon;
import com.cfilmcloud.collate.orm.repository.TSysTaskCheckResultCommonDao;

@Repository
@SuppressWarnings("unchecked")
public class TSysTaskCheckResultCommonDaoImpl implements TSysTaskCheckResultCommonDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<TSysTaskCheckResultCommon> select(String table) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_task_check_result_");
		sql.append(table);
		sql.append(" where 1=1 ");
		List<TSysTaskCheckResultCommon> list = this.em
				.createNativeQuery(sql.toString(), TSysTaskCheckResultCommon.class).getResultList();
		return list;
	}

	@Override
	public Integer count(String table) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from t_sys_task_check_result_");
		sql.append(table);
		sql.append(" where 1=1 ");
		Number count = (Number) this.em.createNativeQuery(sql.toString()).getSingleResult();
		return count.intValue();
	}

}
