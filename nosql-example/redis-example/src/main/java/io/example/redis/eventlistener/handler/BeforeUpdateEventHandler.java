package io.example.redis.eventlistener.handler;

import io.example.redis.eventlistener.AnnotationAuditingMetadata;
import org.springframework.data.keyvalue.core.event.KeyValueEvent;
import org.springframework.data.util.ReflectionUtils;

/**
 * Redis键值事件处理器：更新前事件
 *
 * @author zww
 * @since 2023-08-11 18:03:17
 */
public class BeforeUpdateEventHandler implements KeyValueEventHandler {
    public static final String BEAN_NAME = "BeforeUpdateEventHandler";

    @Override
    public <T> void eventHandle(KeyValueEvent<T> event) {
        if (event instanceof KeyValueEvent.BeforeUpdateEvent<T> beforeUpdate) {
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
}
