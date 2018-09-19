package com.demo.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.demo.domain.Namespace;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午8:20:02
 * @description Oracle数据库验证通过
 */
public interface NamespaceRepository extends JpaRepository<Namespace, Long> {
	List<Namespace> findByAppIdAndClusterNameOrderByIdAsc(String appId, String clusterName);

	Namespace findByAppIdAndClusterNameAndNamespaceName(String appId, String clusterName, String namespaceName);

	@Modifying
	@Query("update Namespace set isdeleted=1,DataChange_LastModifiedBy = ?3 where appId=?1 and clusterName=?2")
	int batchDelete(String appId, String clusterName, String operator);

	List<Namespace> findByAppIdAndNamespaceNameOrderByIdAsc(String appId, String namespaceName);

	List<Namespace> findByNamespaceName(String namespaceName, Pageable page);

	int countByNamespaceNameAndAppIdNot(String namespaceName, String appId);
}
