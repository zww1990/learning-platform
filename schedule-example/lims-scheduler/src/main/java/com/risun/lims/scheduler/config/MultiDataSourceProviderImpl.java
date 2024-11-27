package com.risun.lims.scheduler.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源提供者实现类
 * @author 张维维
 * @since 2024-11-25 13:21:23
 */
@Configuration
@EnableConfigurationProperties(MultiDataSourceProperties.class)
@AllArgsConstructor
public class MultiDataSourceProviderImpl implements MultiDataSourceProvider {
    private final MultiDataSourceProperties dataSourceProperties;

    @Override
    public Map<DataSourceKey, DataSource> loadDataSources() {
        if (dataSourceProperties.getDatasources() != null) {
            Map<DataSourceKey, DataSource> dataSources = new HashMap<>(dataSourceProperties.getDatasources().size());
            dataSourceProperties.getDatasources().forEach((k, v) -> dataSources.put(k, new HikariDataSource(v)));
            return dataSources;
        }
        return Collections.emptyMap();
    }
}
