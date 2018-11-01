package com.example.dubbo.filter;

import java.util.Arrays;
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
		log.info("[{}]方法调用前", Constants.CONSUMER);
		long start = System.currentTimeMillis();
		log.info("接口类名={}", invoker.getInterface());
		log.info("方法名={}", invocation.getMethodName());
		log.info("参数类型列表={}", Arrays.toString(invocation.getParameterTypes()));
		log.info("参数值列表={}", Arrays.toString(invocation.getArguments()));
		// 在服务消费方端设置隐式参数
		String uuid = UUID.randomUUID().toString();
		log.info("RequestID={}", uuid);
		RpcContext.getContext().setAttachment("UUID", uuid);
		Result result = invoker.invoke(invocation);
		long elapsed = System.currentTimeMillis() - start;
		log.info("调用时长={}ms", elapsed);
		log.info("[{}]方法调用后", Constants.CONSUMER);
		return result;
	}
}
