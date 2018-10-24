package com.example.dubbo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ZhangWeiWei
 * @date 2018年10月24日,下午6:34:12
 * @description Dubbo属性配置类
 */
@PropertySource("classpath:dubbo.properties")
public class DubboProperties {
	@Value("${dubbo.annotation.package}")
	private String annotationPackage;
	@Value("${dubbo.app.name}")
	private String appName;
	@Value("${dubbo.registry.address}")
	private String registryAddress;
	@Value("${dubbo.protocol.name}")
	private String protocolName;
	@Value("${dubbo.protocol.port}")
	private int protocolPort;

	public String getAnnotationPackage() {
		return annotationPackage;
	}

	public void setAnnotationPackage(String annotationPackage) {
		this.annotationPackage = annotationPackage;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public int getProtocolPort() {
		return protocolPort;
	}

	public void setProtocolPort(int protocolPort) {
		this.protocolPort = protocolPort;
	}

}
