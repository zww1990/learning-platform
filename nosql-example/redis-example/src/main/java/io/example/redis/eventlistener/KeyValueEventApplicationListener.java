package io.example.redis.eventlistener;

import io.example.redis.eventlistener.handler.KeyValueEventHandler;
import io.example.redis.eventlistener.handler.KeyValueEventHandlerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
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
    //方法一
    @Autowired
    private Map<String, KeyValueEventHandler> eventHandlerMap;
    //方法二
    @Autowired(required = false)
    private KeyValueEventHandlerFactory keyValueEventHandlerFactory;

    public KeyValueEventApplicationListener() {
        log.debug("初始化Redis键值事件监听器");
    }

    @Override
    public void onApplicationEvent(KeyValueEvent<Object> event) {
        String beanName = event.getClass().getSimpleName() + "Handler";
        //方法一
//        Optional.ofNullable(this.eventHandlerMap.get(beanName))
//                .ifPresentOrElse(
//                        act -> act.eventHandle(event),
//                        () -> log.debug("不处理此类型的事件 - {}", event));
        //方法二
        try {
            keyValueEventHandlerFactory.getKeyValueEventHandler(beanName).eventHandle(event);
        } catch (NoSuchBeanDefinitionException e) {
            log.debug(e.getLocalizedMessage(), e);
        }
    }

}
