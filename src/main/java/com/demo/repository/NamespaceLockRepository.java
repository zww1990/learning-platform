package com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.domain.NamespaceLock;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午8:09:50
 * @description Oracle数据库验证通过
 */
public interface NamespaceLockRepository extends PagingAndSortingRepository<NamespaceLock, Long> {
	NamespaceLock findByNamespaceId(long namespaceId);

	Long deleteByNamespaceId(Long namespaceId);
}
