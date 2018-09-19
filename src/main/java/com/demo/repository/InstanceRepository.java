package com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.domain.Instance;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午7:52:57
 * @description Oracle数据库验证通过
 */
public interface InstanceRepository extends PagingAndSortingRepository<Instance, Long> {
	Instance findByAppIdAndClusterNameAndDataCenterAndIp(String appId, String clusterName, String dataCenter,
			String ip);
}
