package io.example.redis.eventlistener;

import io.example.redis.eventlistener.handler.BeforeInsertEventHandler;
import io.example.redis.eventlistener.handler.BeforeUpdateEventHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.keyvalue.core.KeyValueTemplate;

/**
 * Redis键值事件监听器配置类
 *
 * @author zww
 * @since 2023-08-11 21:10:51
 */
@ConditionalOnClass(KeyValueTemplate.class)
@ConditionalOnProperty(prefix = "key-value-event", name = "enabled", havingValue = "true", matchIfMissing = true)
@Configuration
public class KeyValueEventConfig {
    @ConditionalOnMissingBean
    @Bean
    KeyValueEventApplicationListener keyValueEventApplicationListener() {
        return new KeyValueEventApplicationListener();
    }

    @ConditionalOnMissingBean
    @Bean(name = BeforeInsertEventHandler.BEAN_NAME)
    BeforeInsertEventHandler beforeInsertEventHandler() {
        return new BeforeInsertEventHandler();
    }

    @ConditionalOnMissingBean
    @Bean(name = BeforeUpdateEventHandler.BEAN_NAME)
    BeforeUpdateEventHandler beforeUpdateEventHandler() {
        return new BeforeUpdateEventHandler();
    }

}
