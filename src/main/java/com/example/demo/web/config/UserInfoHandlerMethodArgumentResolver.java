package com.example.demo.web.config;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.demo.web.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInfoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	private static final Logger log = LoggerFactory.getLogger(UserInfoHandlerMethodArgumentResolver.class);
	@Resource
	private ObjectMapper objectMapper;

	public UserInfoHandlerMethodArgumentResolver() {
		super();
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 参数类型是否指定UserInfo注解
		boolean hasAnnotation = parameter.hasParameterAnnotation(UserInfo.class);
		// 参数类型是否是UserModel类型
		boolean isAssignable = UserModel.class.isAssignableFrom(parameter.getParameterType());
		return hasAnnotation && isAssignable;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 获取UserInfo类型实例
		UserInfo userInfo = parameter.getParameterAnnotation(UserInfo.class);
		// 获取请求头参数的key
		String headerName = userInfo.name();
		// 获取请求头参数是否是必须参数
		boolean required = userInfo.required();
		// 获取请求头参数值，参数值是经过base64编码的
		String headerValue = webRequest.getHeader(headerName);
		boolean hasValue = StringUtils.hasText(headerValue);
		if (required && !hasValue) {
			throw new MissingServletRequestParameterException(headerName, "request header");
		}
		if (!hasValue) {
			return null;
		}
		// 需要对参数值进行base64解码
		headerValue = new String(Base64Utils.decodeFromString(headerValue));
		log.info("解析用户信息>>>headerName={}, headerValue={}", headerName, headerValue);
		try {
			UserModel value = this.objectMapper.readValue(headerValue, UserModel.class);
			return value;
		} catch (Exception e) {
			throw new ServletRequestBindingException("请求头[" + headerName + "]参数无效", e);
		}
	}

}
