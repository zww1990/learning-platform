package com.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.demo.domain.App;

/**
 * @author ZhangWeiWei
 * @date 2018年9月19日,下午4:44:33
 * @description oracle数据库验证通过
 */
public interface AppRepository extends JpaRepository<App, Long> {
	@Query("SELECT a from App a WHERE a.name LIKE %:name%")
	List<App> findByName(@Param("name") String name);

	App findByAppId(String appId);
}
