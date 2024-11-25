package com.risun.lims.scheduler.config;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 多数据源注解
 * @author 张维维
 * @since 2024-11-25 14:04:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DataSource {
    /**
     * 数据源名称
     */
    @AliasFor("value")
    DataSourceKey dataSourceName() default DataSourceKey.FIRST;

    /**
     * 数据源名称
     */
    @AliasFor("dataSourceName")
    DataSourceKey value() default DataSourceKey.FIRST;
}
