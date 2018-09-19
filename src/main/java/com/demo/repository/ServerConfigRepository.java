package com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.domain.ServerConfig;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public interface ServerConfigRepository extends PagingAndSortingRepository<ServerConfig, Long> {
	ServerConfig findTopByKeyAndCluster(String key, String cluster);
}
