package com.risun.lims.scheduler.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
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
@Slf4j
public class MultiDataSourceProviderImpl implements MultiDataSourceProvider {
    private final MultiDataSourceProperties dataSourceProperties;

    @Override
    public Map<DataSourceKey, DataSource> loadDataSources() {
        if (dataSourceProperties.getDatasources() != null) {
            Map<DataSourceKey, DataSource> dataSources = new HashMap<>(dataSourceProperties.getDatasources().size());
            dataSourceProperties.getDatasources().forEach((k, v) -> {
                try {
                    v.init();
                    dataSources.put(k, v);
                } catch (SQLException e) {
                    log.error(e.getLocalizedMessage(), e);
                }
            });
            return dataSources;
        }
        return Collections.emptyMap();
    }
}
