package com.example.dubbo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ZhangWeiWei
 * @date 2018年10月24日,下午7:54:28
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

}
