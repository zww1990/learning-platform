package com.example.oauth2authorizationserver.config;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public class MyJdbcRegisteredClientRepository extends JdbcRegisteredClientRepository {

	public MyJdbcRegisteredClientRepository(JdbcOperations jdbcOperations) {
		super(jdbcOperations);
	}

	@Override
	public void save(RegisteredClient registeredClient) {
		RegisteredClient findByClientId = super.findByClientId(registeredClient.getClientId());
		super.save(findByClientId == null ? registeredClient : findByClientId);
	}
}
