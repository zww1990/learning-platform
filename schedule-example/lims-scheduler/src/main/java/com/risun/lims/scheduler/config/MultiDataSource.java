package com.risun.lims.scheduler.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源
 * @author 张维维
 * @since 2024-11-25 13:42:14
 */
@Component
public class MultiDataSource extends AbstractRoutingDataSource {

    /**
     * 构造多数据源
     */
    public MultiDataSource(MultiDataSourceProvider multiDataSourceProvider) {
        Map<Object, Object> targetDataSources = new HashMap<>(multiDataSourceProvider.loadDataSources());
        super.setTargetDataSources(targetDataSources);
        super.setDefaultTargetDataSource(targetDataSources.get(MultiDataSourceProvider.DEFAULT_DATASOURCE));
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return MultiDataSourceContextHolder.getDataSourceKey();
    }
}
