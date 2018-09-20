package com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.domain.Instance;

public interface InstanceRepository extends PagingAndSortingRepository<Instance, Long> {
  Instance findByAppIdAndClusterNameAndDataCenterAndIp(String appId, String clusterName, String dataCenter, String ip);
}
