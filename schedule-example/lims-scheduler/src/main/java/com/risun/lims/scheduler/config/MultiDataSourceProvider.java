package com.risun.lims.scheduler.config;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 多数据源提供者
 * @author 张维维
 * @since 2024-11-25 13:16:33
 */
public interface MultiDataSourceProvider {
    /**
     * 默认数据源
     */
    DataSourceKey DEFAULT_DATASOURCE = DataSourceKey.FIRST;
    /**
     * 加载所有的数据源
     */
    Map<DataSourceKey, DataSource> loadDataSources();

}
