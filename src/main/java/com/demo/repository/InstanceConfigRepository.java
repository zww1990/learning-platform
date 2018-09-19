package com.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.demo.domain.InstanceConfig;

public interface InstanceConfigRepository extends PagingAndSortingRepository<InstanceConfig, Long> {
	InstanceConfig findByInstanceIdAndConfigAppIdAndConfigNamespaceName(long instanceId, String configAppId,
			String configNamespaceName);

	Page<InstanceConfig> findByReleaseKeyAndDataChangeLastModifiedTimeAfter(String releaseKey, Date validDate,
			Pageable pageable);

	Page<InstanceConfig> findByConfigAppIdAndConfigClusterNameAndConfigNamespaceNameAndDataChangeLastModifiedTimeAfter(
			String appId, String clusterName, String namespaceName, Date validDate, Pageable pageable);

	List<InstanceConfig> findByConfigAppIdAndConfigClusterNameAndConfigNamespaceNameAndDataChangeLastModifiedTimeAfterAndReleaseKeyNotIn(
			String appId, String clusterName, String namespaceName, Date validDate, Set<String> releaseKey);

	@Modifying
	@Query("delete from InstanceConfig  where ConfigAppId=?1 and ConfigClusterName=?2 and ConfigNamespaceName = ?3")
	int batchDelete(String appId, String clusterName, String namespaceName);

	@Query(value = "SELECT b.id FROM instanceconfig a INNER JOIN instance b ON b.id=a.instanceid WHERE a.configappid= :configAppId AND a.configclustername= :clusterName AND a.confignamespacename= :namespaceName AND a.datachange_lasttime> :validDate AND b.appid= :instanceAppId AND ?#{#pageable.pageSize} > 0", countQuery = "SELECT COUNT(1) FROM instanceconfig a INNER JOIN instance b ON b.id=a.instanceid WHERE a.configappid= :configAppId AND a.configclustername= :clusterName AND a.confignamespacename= :namespaceName AND a.datachange_lasttime> :validDate AND b.appid= :instanceAppId", nativeQuery = true)
	Page<Object[]> findInstanceIdsByNamespaceAndInstanceAppId(@Param("instanceAppId") String instanceAppId,
			@Param("configAppId") String configAppId, @Param("clusterName") String clusterName,
			@Param("namespaceName") String namespaceName, @Param("validDate") Date validDate, Pageable pageable);
}
