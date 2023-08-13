package io.example.redis.eventlistener.handler;

import io.example.redis.eventlistener.AnnotationAuditingMetadata;
import org.springframework.data.keyvalue.core.event.KeyValueEvent;
import org.springframework.data.util.ReflectionUtils;

/**
 * Redis键值事件处理器：插入前事件
 *
 * @author zww
 * @since 2023-08-11 18:03:51
 */
public class BeforeInsertEventHandler implements KeyValueEventHandler {
    public static final String BEAN_NAME = "BeforeInsertEventHandler";

    @Override
    public <T> void eventHandle(KeyValueEvent<T> event) {
        if (event instanceof KeyValueEvent.BeforeInsertEvent<T> beforeInsert) {
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
        } else {
            log.debug("不处理此类型的事件 - {}", event);
        }
    }
}
