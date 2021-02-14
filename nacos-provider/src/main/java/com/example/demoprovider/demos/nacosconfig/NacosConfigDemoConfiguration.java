package com.example.demoprovider.demos.nacosconfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(User.class)
public class NacosConfigDemoConfiguration {

}
