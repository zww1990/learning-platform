package com.risun.lims.scheduler.config;

import lombok.extern.slf4j.Slf4j;

/**
 * 多数据源上下文
 * @author 张维维
 * @since 2024-11-25 13:40:29
 */
@Slf4j
public class MultiDataSourceContextHolder {
    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<DataSourceKey> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源的变量
     */
    public static void setDataSourceKey(DataSourceKey key) {
        log.info("切换到[{}]数据源", key);
        CONTEXT_HOLDER.set(key);
    }

    /**
     * 获得数据源的变量
     */
    public static DataSourceKey getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据源变量
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }
}
