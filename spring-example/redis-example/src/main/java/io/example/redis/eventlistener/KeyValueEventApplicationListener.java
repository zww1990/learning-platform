package io.example.redis.eventlistener;

import io.example.redis.eventlistener.handler.KeyValueEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.keyvalue.core.event.KeyValueEvent;

import java.util.Map;
import java.util.Optional;

/**
 * 自定义Redis键值事件监听器
 *
 * @author zww
 * @since 2023-08-11 13:43:44
 */
@Slf4j
public class KeyValueEventApplicationListener implements ApplicationListener<KeyValueEvent<Object>> {
    @Autowired
    private Map<String, KeyValueEventHandler> eventHandlerMap;

    public KeyValueEventApplicationListener() {
        log.debug("初始化Redis键值事件监听器");
    }

    @Override
    public void onApplicationEvent(KeyValueEvent<Object> event) {
        Optional.ofNullable(this.eventHandlerMap.get(event.getClass().getSimpleName() + "Handler"))
                .ifPresentOrElse(
                        act -> act.eventHandle(event),
                        () -> log.debug("不处理此类型的事件 - {}", event));
    }

}
