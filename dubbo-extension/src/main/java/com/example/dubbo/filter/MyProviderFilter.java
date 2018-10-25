package com.example.dubbo.filter;

import java.util.Arrays;
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
		log.info("[{}]方法调用前", Constants.PROVIDER);
		long start = System.currentTimeMillis();
		log.info("接口类名={}", invoker.getInterface());
		log.info("方法名={}", invocation.getMethodName());
		log.info("参数类型列表={}", Arrays.toString(invocation.getParameterTypes()));
		log.info("参数值列表={}", Arrays.toString(invocation.getArguments()));
		// 在服务提供方端获取隐式参数
		log.info("附加参数UUID={}", RpcContext.getContext().getAttachment("UUID"));
		Result result = invoker.invoke(invocation);
		long elapsed = System.currentTimeMillis() - start;
		log.info("调用时长={}ms", elapsed);
		log.info("[{}]方法调用后", Constants.PROVIDER);
		return result;
	}
}
