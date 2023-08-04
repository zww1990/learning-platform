package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * Eureka注册中心启动类
 * 
 * @author zhang weiwei
 * @since 2023年8月1日,下午8:56:31
 */
@SpringBootApplication
@EnableEurekaServer
@EnableWebSecurity
@Slf4j
public class EurekaServer02Application implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EurekaServer02Application.class, args);
		log.info("Get Bean Definition Count = {}", context.getBeanDefinitionCount());
//		java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@Override
	public void customize(UndertowServletWebServerFactory factory) {
		// 解决undertow（UT026010）启动警告：给WebSocketDeploymentInfo设置缓冲池
		factory.addDeploymentInfoCustomizers(deploymentInfo -> {
			WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
			webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
			deploymentInfo.addServletContextAttribute(WebSocketDeploymentInfo.ATTRIBUTE_NAME, webSocketDeploymentInfo);
		});
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http//
				.httpBasic(Customizer.withDefaults())//
				.formLogin(Customizer.withDefaults())//
				.logout(Customizer.withDefaults())//
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())//
				.csrf(csrf -> csrf.disable())//
				.build();
	}

}
