package com.example.demo;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.example.demo.config.RootConfig;
import com.example.demo.config.ServletConfig;

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

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter(Charset.forName("UTF-8").name(), true);
		return ArrayUtils.toArray(encodingFilter);
	}
}
