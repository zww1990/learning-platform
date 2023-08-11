package io.example.redis.eventlistener.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.keyvalue.core.event.KeyValueEvent;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Redis键值事件处理器
 *
 * @author zww
 * @since 2023-08-11 17:51:29
 */
public interface KeyValueEventHandler {
    Logger log = LoggerFactory.getLogger(KeyValueEventHandler.class);

    /**
     * 事件处理
     *
     * @param event {@link KeyValueEvent}
     * @since 2023-08-11 17:54:48
     */
    <T> void eventHandle(KeyValueEvent<T> event);

    /**
     * 给字段设置具体的值
     *
     * @param field {@link Field}
     * @return {@link java.lang.Object}
     * @since 2023-08-11 17:58:01
     */
    default Object setFieldValue(Field field) {
        log.debug("字段类型：{}", field.getType());
        if (ClassUtils.isAssignable(field.getType(), long.class)
                || ClassUtils.isAssignable(field.getType(), Long.class)) {
            return System.currentTimeMillis();
        }
        if (ClassUtils.isAssignable(field.getType(), java.util.Date.class)) {
            return new java.util.Date(System.currentTimeMillis());
        }
        if (ClassUtils.isAssignable(field.getType(), Date.class)) {
            return new Date(System.currentTimeMillis());
        }
        if (ClassUtils.isAssignable(field.getType(), Timestamp.class)) {
            return new Timestamp(System.currentTimeMillis());
        }
        if (ClassUtils.isAssignable(field.getType(), Time.class)) {
            return new Time(System.currentTimeMillis());
        }
        if (ClassUtils.isAssignable(field.getType(), Instant.class)) {
            return Instant.now();
        }
        if (ClassUtils.isAssignable(field.getType(), LocalDateTime.class)) {
            return LocalDateTime.now();
        }
        if (ClassUtils.isAssignable(field.getType(), LocalDate.class)) {
            return LocalDate.now();
        }
        if (ClassUtils.isAssignable(field.getType(), LocalTime.class)) {
            return LocalTime.now();
        }
        log.debug("未知的字段类型");
        return null;
    }
}
