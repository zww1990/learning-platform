package com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.domain.NamespaceLock;

public interface NamespaceLockRepository extends PagingAndSortingRepository<NamespaceLock, Long> {

  NamespaceLock findByNamespaceId(long namespaceId);

  Long deleteByNamespaceId(Long namespaceId);

}
