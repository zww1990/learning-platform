package com.example.dubbo.filter;

import java.time.LocalDateTime;
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

@Activate(group = Constants.PROVIDER)
public class MyProviderFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(MyProviderFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		log.info("方法调用前[{}]", LocalDateTime.now());
		// 在服务提供方端获取隐式参数
		log.info("UUID={}", RpcContext.getContext().getAttachment("UUID"));
		Result result = invoker.invoke(invocation);
		log.info("方法调用后[{}]", LocalDateTime.now());
		return result;
	}
}
