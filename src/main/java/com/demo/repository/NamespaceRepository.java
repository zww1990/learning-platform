package com.demo.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.domain.Namespace;

public interface NamespaceRepository extends PagingAndSortingRepository<Namespace, Long> {
	List<Namespace> findByAppIdAndClusterNameOrderByIdAsc(String appId, String clusterName);

	Namespace findByAppIdAndClusterNameAndNamespaceName(String appId, String clusterName, String namespaceName);

	@Modifying
	@Query("update Namespace set isdeleted=1,DataChange_LastModifiedBy = ?3 where appId=?1 and clusterName=?2")
	int batchDelete(String appId, String clusterName, String operator);

	List<Namespace> findByAppIdAndNamespaceNameOrderByIdAsc(String appId, String namespaceName);

	List<Namespace> findByNamespaceName(String namespaceName, Pageable page);

	int countByNamespaceNameAndAppIdNot(String namespaceName, String appId);
}
