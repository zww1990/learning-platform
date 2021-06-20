package com.example.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring根配置类
 * 
 * @author home
 */
@Configuration
@Import({ CasProperties.class, CasAutoConfig.class })
public class RootConfig {

}
