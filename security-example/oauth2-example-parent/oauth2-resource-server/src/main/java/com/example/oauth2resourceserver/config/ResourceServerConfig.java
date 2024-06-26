package com.example.oauth2resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 资源服务配置
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午6:06:55
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/messages/**")
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers("/messages/**").hasAuthority("SCOPE_message.read"))
				.oauth2ResourceServer(server -> server.jwt(Customizer.withDefaults()));
		return http.build();
	}

}
