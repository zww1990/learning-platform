package com.example.dubbo.service.api;

import com.example.dubbo.service.domain.Hello;

/**
 * 示例服务接口
 * 
 * @author zhang weiwei
 * @since 2023年8月2日,下午2:56:31
 */
public interface HelloService {
	Hello get(String name);
}
