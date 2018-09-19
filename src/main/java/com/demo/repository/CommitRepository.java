package com.demo.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.demo.domain.Commit;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午5:19:21
 * @description oracle数据库验证通过
 */
public interface CommitRepository extends JpaRepository<Commit, Long> {
	List<Commit> findByAppIdAndClusterNameAndNamespaceNameOrderByIdDesc(String appId, String clusterName,
			String namespaceName, Pageable pageable);

	@Modifying
	@Query("update Commit set isdeleted=1,DataChange_LastModifiedBy = ?4 where appId=?1 and clusterName=?2 and namespaceName = ?3")
	int batchDelete(String appId, String clusterName, String namespaceName, String operator);
}
