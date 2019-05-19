package com.example.dubbo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:redis.properties")
public class RedisProperties {
	@Value("${redis.sentinel.master}")
	private String sentinelMaster;
	@Value("${redis.sentinel.nodes}")
	private String[] sentinelNodes;
	@Value("${redis.database}")
	private int database;
	@Value("${redis.password}")
	private String password;
	@Value("${redis.pool.max-active}")
	private int maxActive;
	@Value("${redis.pool.max-idle}")
	private int maxIdle;
	@Value("${redis.pool.max-wait}")
	private long maxWait;
	@Value("${redis.pool.min-idle}")
	private int minIdle;
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private int port;

	public String getSentinelMaster() {
		return sentinelMaster;
	}

	public String[] getSentinelNodes() {
		return sentinelNodes;
	}

	public int getDatabase() {
		return database;
	}

	public String getPassword() {
		return password;
	}

	public void setSentinelMaster(String sentinelMaster) {
		this.sentinelMaster = sentinelMaster;
	}

	public void setSentinelNodes(String[] sentinelNodes) {
		this.sentinelNodes = sentinelNodes;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
