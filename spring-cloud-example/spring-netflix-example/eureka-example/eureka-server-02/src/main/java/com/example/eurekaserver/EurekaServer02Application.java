package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaServer
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

}
