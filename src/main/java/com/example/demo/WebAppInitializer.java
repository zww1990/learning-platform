package com.example.demo;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.example.demo.root.AppConfig;
import com.example.demo.web.config.DispatcherConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return ArrayUtils.<Class<?>>toArray(AppConfig.class);
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return ArrayUtils.<Class<?>>toArray(DispatcherConfig.class);
	}

	@Override
	protected String[] getServletMappings() {
		return ArrayUtils.toArray("/");
	}
}
