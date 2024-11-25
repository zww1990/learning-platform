package com.risun.lims.scheduler.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 多数据源切面
 * @author 张维维
 * @since 2024-11-25 14:13:37
 */
@Component
@Order(1)
@Aspect
public class MultiDataSourceAspect {
    @Pointcut("@annotation(com.risun.lims.scheduler.config.DataSource) || @within(com.risun.lims.scheduler.config.DataSource)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = this.getDataSource(point);

        if (Objects.nonNull(dataSource)) {
            MultiDataSourceContextHolder.setDataSourceKey(dataSource.dataSourceName());
        }

        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            MultiDataSourceContextHolder.clearDataSourceKey();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    private DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 在定义的方法上查找DataSource注解
        DataSource dataSource = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
        if (Objects.nonNull(dataSource)) {
            return dataSource;
        }
        // 在定义的类上查找DataSource注解
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
    }
}
