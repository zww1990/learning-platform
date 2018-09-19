package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.domain.ServerConfig;

/**
 * Oracle数据库验证通过
 * @author Jason Song(song_s@ctrip.com)
 */
public interface ServerConfigRepository extends JpaRepository<ServerConfig, Long> {
	ServerConfig findTopByKeyAndCluster(String key, String cluster);
}
