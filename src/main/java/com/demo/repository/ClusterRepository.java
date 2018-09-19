package com.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.domain.Cluster;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午5:02:36
 * @description oracle数据库验证通过
 */
public interface ClusterRepository extends JpaRepository<Cluster, Long> {
	List<Cluster> findByAppIdAndParentClusterId(String appId, Long parentClusterId);

	List<Cluster> findByAppId(String appId);

	Cluster findByAppIdAndName(String appId, String name);

	List<Cluster> findByParentClusterId(long parentClusterId);
}
