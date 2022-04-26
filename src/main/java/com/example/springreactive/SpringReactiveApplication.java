package com.example.springreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.core.DatabaseClient;

import io.r2dbc.spi.ConnectionFactory;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class SpringReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveApplication.class, args);
	}

	@Bean
	public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
		return DatabaseClient.builder().connectionFactory(connectionFactory).build();
	}
}
