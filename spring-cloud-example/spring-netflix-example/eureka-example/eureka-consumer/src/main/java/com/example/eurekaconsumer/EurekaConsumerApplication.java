package com.example.eurekaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.eurekaconsumer.service.HelloService;

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

	/**
	 * 创建接口实例
	 * 
	 * @author zhang weiwei
	 * @since 2023年8月1日,下午10:09:14
	 * @return
	 */
	@Bean
	HelloService helloService() {
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(WebClient.create())).build();
		return factory.createClient(HelloService.class);
	}
}
