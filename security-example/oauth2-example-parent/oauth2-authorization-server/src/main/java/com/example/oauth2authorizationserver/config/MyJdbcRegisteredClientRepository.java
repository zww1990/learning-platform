package com.example.oauth2authorizationserver.config;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

/**
 * 扩展Jdbc注册客户端存储库
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午7:55:02
 */
public class MyJdbcRegisteredClientRepository extends JdbcRegisteredClientRepository {

	public MyJdbcRegisteredClientRepository(JdbcOperations jdbcOperations) {
		super(jdbcOperations);
	}

	@Override
	public void save(RegisteredClient registeredClient) {
		// 重写父类的持久化方法
		RegisteredClient findByClientId = super.findByClientId(registeredClient.getClientId());
		super.save(findByClientId == null ? registeredClient : findByClientId);
	}
}
