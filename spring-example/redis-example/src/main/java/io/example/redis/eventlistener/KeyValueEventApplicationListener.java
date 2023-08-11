package io.example.redis.eventlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.data.keyvalue.core.event.KeyValueEvent;
import org.springframework.data.util.ReflectionUtils;
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
 * 自定义Redis键值事件监听器
 *
 * @author zww
 * @since 2023-08-11 13:43:44
 */
@Slf4j
public class KeyValueEventApplicationListener implements ApplicationListener<KeyValueEvent<Object>> {

    public KeyValueEventApplicationListener() {
        log.debug("初始化Redis键值事件监听器");
    }

    @Override
    public void onApplicationEvent(KeyValueEvent<Object> event) {
        if (event instanceof KeyValueEvent.BeforeInsertEvent<Object> beforeInsert) {
            log.debug("插入前事件 - 开始：keyspace = {}, key = {}, payload = {}, timstamp = {}, type = {}",
                    beforeInsert.getKey(),
                    beforeInsert.getKeyspace(),
                    beforeInsert.getPayload(),
                    beforeInsert.getTimestamp(),
                    beforeInsert.getType());
            if (beforeInsert.getPayload() == null) {
                log.debug("插入前事件 - 结束：payload = null，不处理");
                return;
            }
            AnnotationAuditingMetadata metadata = AnnotationAuditingMetadata.getMetadata(beforeInsert.getType());
            log.debug("是否可审核：{}", metadata.isAuditable());
            if (metadata.isAuditable()) {
                metadata.getCreatedDateField().ifPresent(field -> {
                    Object value = this.setFieldValue(field);
                    ReflectionUtils.setField(field, beforeInsert.getPayload(), value);
                    log.debug("插入前事件 - 结束：已设置创建时间");
                });
                metadata.getLastModifiedDateField().ifPresent(field -> {
                    Object value = this.setFieldValue(field);
                    ReflectionUtils.setField(field, beforeInsert.getPayload(), value);
                    log.debug("插入前事件 - 结束：已设置最后修改时间");
                });
            } else {
                log.debug("插入前事件 - 结束");
            }
        } else if (event instanceof KeyValueEvent.BeforeUpdateEvent<Object> beforeUpdate) {
            log.debug("更新前事件 - 开始：keyspace = {}, key = {}, payload = {}, timstamp = {}, type = {}",
                    beforeUpdate.getKey(),
                    beforeUpdate.getKeyspace(),
                    beforeUpdate.getPayload(),
                    beforeUpdate.getTimestamp(),
                    beforeUpdate.getType());
            if (beforeUpdate.getPayload() == null) {
                log.debug("更新前事件 - 结束：payload = null，不处理");
                return;
            }
            AnnotationAuditingMetadata metadata = AnnotationAuditingMetadata.getMetadata(beforeUpdate.getType());
            log.debug("是否可审核：{}", metadata.isAuditable());
            if (metadata.isAuditable()) {
                metadata.getLastModifiedDateField().ifPresent(field -> {
                    Object value = this.setFieldValue(field);
                    ReflectionUtils.setField(field, beforeUpdate.getPayload(), value);
                    log.debug("更新前事件 - 结束：已设置最后修改时间");
                });
            } else {
                log.debug("更新前事件 - 结束");
            }
        } else {
            log.debug("不处理此类型的事件 - {}", event);
        }
    }

    private Object setFieldValue(Field field) {
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
