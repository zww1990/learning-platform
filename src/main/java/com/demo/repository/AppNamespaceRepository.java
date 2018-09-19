package com.demo.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.demo.domain.AppNamespace;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午4:24:57
 * @description oracle数据库验证通过
 */
public interface AppNamespaceRepository extends JpaRepository<AppNamespace, Long> {
	AppNamespace findByAppIdAndName(String appId, String namespaceName);

	List<AppNamespace> findByAppIdAndNameIn(String appId, Set<String> namespaceNames);

	AppNamespace findByNameAndIsPublicTrue(String namespaceName);

	List<AppNamespace> findByNameInAndIsPublicTrue(Set<String> namespaceNames);

	List<AppNamespace> findByAppIdAndIsPublic(String appId, boolean isPublic);

	List<AppNamespace> findByAppId(String appId);

	List<AppNamespace> findFirst500ByIdGreaterThanOrderByIdAsc(long id);

	@Modifying
	@Query("UPDATE AppNamespace SET IsDeleted=1,DataChange_LastModifiedBy = ?2 WHERE AppId=?1")
	int batchDeleteByAppId(String appId, String operator);

	@Modifying
	@Query("UPDATE AppNamespace SET IsDeleted=1,DataChange_LastModifiedBy = ?3 WHERE AppId=?1 and Name = ?2")
	int delete(String appId, String namespaceName, String operator);
}
