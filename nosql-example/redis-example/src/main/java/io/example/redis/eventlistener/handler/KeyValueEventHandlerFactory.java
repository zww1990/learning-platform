package io.example.redis.eventlistener.handler;

/**
 * Redis键值事件处理器工厂
 *
 * @author zww
 * @since 2023-08-26 09:29:32
 */
public interface KeyValueEventHandlerFactory {
    /**
     * 获取Redis键值事件处理器
     *
     * @param beanName spring容器bean名称
     * @return {@link KeyValueEventHandler}
     * @since 2023-08-26 09:30:01
     */
    KeyValueEventHandler getKeyValueEventHandler(String beanName);
}
