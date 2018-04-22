package com.example.demo;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.example.demo.root.RootConfig;
import com.example.demo.web.config.ServletConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return ArrayUtils.<Class<?>>toArray(RootConfig.class);
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return ArrayUtils.<Class<?>>toArray(ServletConfig.class);
	}

	@Override
	protected String[] getServletMappings() {
		return ArrayUtils.toArray("/");
	}
}
