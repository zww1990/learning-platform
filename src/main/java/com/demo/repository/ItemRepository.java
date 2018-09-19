package com.demo.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.demo.domain.Item;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午8:04:06
 * @description Oracle数据库验证通过
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
	Item findByNamespaceIdAndKey(Long namespaceId, String key);

	List<Item> findByNamespaceIdOrderByLineNumAsc(Long namespaceId);

	List<Item> findByNamespaceId(long namespaceId);

	List<Item> findByNamespaceIdAndDataChangeLastModifiedTimeGreaterThan(Long namespaceId, Date date);

	Item findFirst1ByNamespaceIdOrderByLineNumDesc(Long namespaceId);

	@Modifying
	@Query("update Item set isdeleted=1,DataChange_LastModifiedBy = ?2 where namespaceId = ?1")
	int deleteByNamespaceId(long namespaceId, String operator);
}
