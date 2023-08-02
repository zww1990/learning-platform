package com.example.eurekaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务消费者启动类
 * 
 * @author zhang weiwei
 * @since 2023年8月1日,下午9:20:25
 */
@SpringBootApplication
//要扫描注解组件的基本包。
@EnableFeignClients(basePackages = "com.example.provider.api.service")
@Slf4j
public class EurekaConsumerApplication implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EurekaConsumerApplication.class, args);
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
