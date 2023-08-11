package io.example.redis.eventlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.data.keyvalue.core.event.KeyValueEvent;
import org.springframework.data.util.ReflectionUtils;

import java.time.LocalDateTime;

/**
 * 自定义Redis键值事件监听器
 *
 * @author zww
 * @since 2023-08-11 13:43:44
 */
@Slf4j
public class KeyValueEventApplicationListener implements ApplicationListener<KeyValueEvent<Object>> {

    @Override
    public void onApplicationEvent(KeyValueEvent<Object> event) {
        if (event instanceof KeyValueEvent.BeforeInsertEvent<Object> beforeInsert) {
            log.info("插入前事件 - 开始：keyspace = {}, key = {}, payload = {}, timstamp = {}, type = {}",
                    beforeInsert.getKey(),
                    beforeInsert.getKeyspace(),
                    beforeInsert.getPayload(),
                    beforeInsert.getTimestamp(),
                    beforeInsert.getType());
            if (beforeInsert.getPayload() == null) {
                log.info("插入前事件 - 结束：payload = null，不处理");
                return;
            }
            AnnotationAuditingMetadata metadata = AnnotationAuditingMetadata.getMetadata(beforeInsert.getType());
            log.info("是否可审核：{}", metadata.isAuditable());
            if (metadata.isAuditable()) {
                metadata.getCreatedDateField().ifPresent(field -> {
                    ReflectionUtils.setField(field, beforeInsert.getPayload(), LocalDateTime.now());
                    log.info("插入前事件 - 结束：已设置创建时间");
                });
                metadata.getLastModifiedDateField().ifPresent(field -> {
                    ReflectionUtils.setField(field, beforeInsert.getPayload(), LocalDateTime.now());
                    log.info("插入前事件 - 结束：已设置最后修改时间");
                });
            } else {
                log.info("插入前事件 - 结束");
            }
        } else if (event instanceof KeyValueEvent.BeforeUpdateEvent<Object> beforeUpdate) {
            log.info("更新前事件 - 开始：keyspace = {}, key = {}, payload = {}, timstamp = {}, type = {}",
                    beforeUpdate.getKey(),
                    beforeUpdate.getKeyspace(),
                    beforeUpdate.getPayload(),
                    beforeUpdate.getTimestamp(),
                    beforeUpdate.getType());
            if (beforeUpdate.getPayload() == null) {
                log.info("更新前事件 - 结束：payload = null，不处理");
                return;
            }
            AnnotationAuditingMetadata metadata = AnnotationAuditingMetadata.getMetadata(beforeUpdate.getType());
            log.info("是否可审核：{}", metadata.isAuditable());
            if (metadata.isAuditable()) {
                metadata.getLastModifiedDateField().ifPresent(field -> {
                    ReflectionUtils.setField(field, beforeUpdate.getPayload(), LocalDateTime.now());
                    log.info("更新前事件 - 结束：已设置最后修改时间");
                });
            } else {
                log.info("更新前事件 - 结束");
            }
        } else {
            log.debug("不处理此类型的事件 - {}", event);
        }
    }
}
