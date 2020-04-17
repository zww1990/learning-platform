package com.example.demo;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.example.demo.config.RootConfig;
import com.example.demo.config.ServletConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return Stream.of(RootConfig.class).toArray(Class[]::new);
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return Stream.of(ServletConfig.class).toArray(Class[]::new);
	}

	@Override
	protected String[] getServletMappings() {
		return Stream.of("/").toArray(String[]::new);
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true);
		return Stream.of(encodingFilter).toArray(Filter[]::new);
	}
}
