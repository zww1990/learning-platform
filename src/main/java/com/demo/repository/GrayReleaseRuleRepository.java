package com.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.domain.GrayReleaseRule;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午7:30:58
 * @description oracle数据库验证通过
 */
public interface GrayReleaseRuleRepository extends JpaRepository<GrayReleaseRule, Long> {
	GrayReleaseRule findTopByAppIdAndClusterNameAndNamespaceNameAndBranchNameOrderByIdDesc(String appId,
			String clusterName, String namespaceName, String branchName);

	List<GrayReleaseRule> findByAppIdAndClusterNameAndNamespaceName(String appId, String clusterName,
			String namespaceName);

	List<GrayReleaseRule> findFirst500ByIdGreaterThanOrderByIdAsc(Long id);
}
