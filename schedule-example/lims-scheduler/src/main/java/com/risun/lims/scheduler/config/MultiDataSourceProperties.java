package com.risun.lims.scheduler.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 多数据源属性配置类
 * @author 张维维
 * @since 2024-11-25 13:17:00
 */
@ConfigurationProperties(prefix = "spring.multi")
@Getter
@Setter
public class MultiDataSourceProperties {
    /**
     * 多数据源配置
     */
    private Map<DataSourceKey, HikariConfig> datasources;
}
