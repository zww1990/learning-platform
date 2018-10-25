package com.example.dubbo.filter;

import java.time.LocalDateTime;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;

@Activate(group = Constants.CONSUMER)
public class MyConsumerFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(MyConsumerFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		log.info("[{}]方法调用前[{}]", Constants.CONSUMER, LocalDateTime.now());
		// 在服务消费方端设置隐式参数
		RpcContext.getContext().setAttachment("UUID", UUID.randomUUID().toString());
		Result result = invoker.invoke(invocation);
		log.info("[{}]方法调用后[{}]", Constants.CONSUMER, LocalDateTime.now());
		return result;
	}
}
