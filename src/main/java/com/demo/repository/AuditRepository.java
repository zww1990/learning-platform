package com.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.demo.domain.Audit;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午4:54:19
 * @description oracle数据库验证通过
 */
public interface AuditRepository extends JpaRepository<Audit, Long> {
	@Query("SELECT a from Audit a WHERE a.dataChangeCreatedBy = :owner")
	List<Audit> findByOwner(@Param("owner") String owner);

	@Query("SELECT a from Audit a WHERE a.dataChangeCreatedBy = :owner AND a.entityName =:entity AND a.opName = :op")
	List<Audit> findAudits(@Param("owner") String owner, @Param("entity") String entity, @Param("op") String op);
}
