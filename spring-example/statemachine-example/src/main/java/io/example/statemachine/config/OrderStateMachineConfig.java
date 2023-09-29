package io.example.statemachine.config;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.example.statemachine.constant.OrderStatus;
import io.example.statemachine.constant.OrderStatusChangeEvent;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableStateMachine(name = "orderStateMachine")
@Slf4j
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {
	@Override
	public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states) throws Exception {
		states.withStates()//
				.initial(OrderStatus.WAIT_PAYMENT)//
				.states(EnumSet.allOf(OrderStatus.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions)
			throws Exception {
		transitions
				// 支付事件:待支付-》待发货
				.withExternal().source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER)
				.event(OrderStatusChangeEvent.PAYED).and()
				// 发货事件:待发货-》待收货
				.withExternal().source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE)
				.event(OrderStatusChangeEvent.DELIVERY).and()
				// 收货事件:待收货-》已完成
				.withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH)
				.event(OrderStatusChangeEvent.RECEIVED);
	}

	@Bean(name = "stateMachineMemPersister")
	static <S, E, T> StateMachinePersister<S, E, T> stateMachineMemPersister(ObjectMapper json) {
		return new DefaultStateMachinePersister<S, E, T>(//
				new StateMachinePersist<S, E, T>() {
					private Map<T, StateMachineContext<S, E>> map = new HashMap<>();

					@Override
					public void write(StateMachineContext<S, E> context, T contextObj) throws Exception {
						log.info("持久化状态机, context:{}, contextObj:{}", json.writeValueAsString(context),
								json.writeValueAsString(contextObj));
						map.put(contextObj, context);
					}

					@Override
					public StateMachineContext<S, E> read(T contextObj) throws Exception {
						log.info("获取状态机, contextObj:{}", json.writeValueAsString(contextObj));
						StateMachineContext<S, E> stateMachineContext = map.get(contextObj);
						log.info("获取状态机结果, stateMachineContext:{}", json.writeValueAsString(stateMachineContext));
						return stateMachineContext;
					}
				});
	}

	@Bean(name = "stateMachineRedisPersister")
	<S, E> RedisStateMachinePersister<S, E> stateMachineRedisPersister(//
			RedisConnectionFactory redisConnectionFactory) {
		return new RedisStateMachinePersister<>(//
				new RepositoryStateMachinePersist<>(//
						new RedisStateMachineContextRepository<>(//
								redisConnectionFactory)));
	}
}
