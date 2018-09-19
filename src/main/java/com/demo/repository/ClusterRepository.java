package com.demo.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.domain.Cluster;

public interface ClusterRepository extends PagingAndSortingRepository<Cluster, Long> {
	List<Cluster> findByAppIdAndParentClusterId(String appId, Long parentClusterId);

	List<Cluster> findByAppId(String appId);

	Cluster findByAppIdAndName(String appId, String name);

	List<Cluster> findByParentClusterId(long parentClusterId);
}
