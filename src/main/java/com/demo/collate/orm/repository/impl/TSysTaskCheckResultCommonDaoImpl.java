package com.demo.collate.orm.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.demo.collate.orm.domain.TSysTaskCheckResultCommon;
import com.demo.collate.orm.repository.TSysTaskCheckResultCommonDao;

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
		Query query = this.em.createNativeQuery(sql.toString(), TSysTaskCheckResultCommon.class);
		List<TSysTaskCheckResultCommon> list = query.getResultList();
		return list;
	}

	@Override
	public Integer count(String table) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from t_sys_task_check_result_");
		sql.append(table);
		sql.append(" where 1=1 ");
		Query query = this.em.createNativeQuery(sql.toString());
		Number count = (Number) query.getSingleResult();
		return count.intValue();
	}

	@Override
	public void dropTable(String table) {
		StringBuilder sql = new StringBuilder();
		sql.append("DROP TABLE IF EXISTS t_ods_");
		sql.append(table);
		sql.append("_a, t_ods_");
		sql.append(table);
		sql.append("_b;");
		Session session = (Session) this.em.getDelegate();
		Query query = session.createNativeQuery(sql.toString());
		query.executeUpdate();
	}

	@Override
	public String taskExecCreateTable(String taskNo, String batchNo, String checkDate) {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("p_task_exec_createtable");
		query.registerStoredProcedureParameter(0, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
		query.setParameter(0, taskNo);
		query.setParameter(1, batchNo);
		query.setParameter(2, checkDate);
		query.execute();
		return (String) query.getOutputParameterValue(3);
	}

	@Override
	public Integer taskCheckResultCreateTable(String taskNo) {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("p_task_check_result_createtable");
		query.registerStoredProcedureParameter(0, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.OUT);
		query.setParameter(0, taskNo);
		query.execute();
		return (Integer) query.getOutputParameterValue(1);
	}

}
