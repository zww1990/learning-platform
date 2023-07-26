package com.example.demo.api;

/**
 * 声明一个接口
 * 
 * @author zhang weiwei
 * @since 2023年7月26日,下午8:36:18
 */
public interface IHelloService {
	/**
	 * 获取消息内容
	 * 
	 * @param text 输入文本
	 * @return 消息内容
	 */
	String getMessage(String text);
}
