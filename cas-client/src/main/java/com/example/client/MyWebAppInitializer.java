package com.example.client;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Conventions;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.example.client.config.CasProperties;
import com.example.client.config.RootConfig;
import com.example.client.config.RootConfig.CasCondition;
import com.example.client.config.ServletConfig;

public class MyWebAppInitializer implements WebApplicationInitializer {
	private static final Logger log = LoggerFactory.getLogger(MyWebAppInitializer.class);
	private static final String DEFAULT_SERVLET_NAME = "dispatcher";
	private static final boolean DEFAULT_ASYNC_SUPPORTED = true;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext rootAppContext = this.createRootApplicationContext();
		servletContext.addListener(new ContextLoaderListener(rootAppContext));
		ServletRegistration.Dynamic registration = servletContext.addServlet(DEFAULT_SERVLET_NAME,
				new DispatcherServlet(this.createServletApplicationContext()));
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
		registration.setAsyncSupported(DEFAULT_ASYNC_SUPPORTED);
		// 为给定的编码创建一个CharacterEncodingFilter。
		this.registerServletFilter(servletContext, new CharacterEncodingFilter(Charset.forName("UTF-8").name(), true));
		CasProperties casProps = rootAppContext.getBean(CasProperties.class);
		if (CasCondition.CAS_MODE.equalsIgnoreCase(casProps.getCasMode())) {
			log.info("DisplayName={}, CasMode={}", rootAppContext.getDisplayName(), casProps.getCasMode());
			// CAS中的Single Sign Out支持包括配置一个SingleSignOutFilter和一个ContextListener。
			// 侦听器，用于检测HTTP会话何时被销毁，并将其从管理会话的映射中删除。 还允许程序性地删除会话。启用CAS单一登出功能。
			servletContext.addListener(new SingleSignOutHttpSessionListener());
			// 请注意，如果您已将CAS Client for Java配置为Web过滤器，则此过滤器必须位于其他过滤器之前。
			DelegatingFilterProxy singleSignOutFilterProxy = new DelegatingFilterProxy("singleSignOutFilter");
			// 通过Spring IoC的配置将在很大程度上取决于DelegatingFilterProxy类。
			DelegatingFilterProxy authenticationFilterProxy = new DelegatingFilterProxy("authenticationFilter");
			DelegatingFilterProxy ticketValidationFilterProxy = new DelegatingFilterProxy("ticketValidationFilter");
			// 包装一个HttpServletRequest，以便getRemoteUser和getPrincipal返回CAS相关的条目。
			HttpServletRequestWrapperFilter requestWrapperFilter = new HttpServletRequestWrapperFilter();
			// 将断言置于ThreadLocal中，以便其他资源可以访问不具有Web层会话的权限。
			AssertionThreadLocalFilter threadLocalFilter = new AssertionThreadLocalFilter();
			List<Filter> filters = Arrays.asList(singleSignOutFilterProxy, authenticationFilterProxy,
					ticketValidationFilterProxy, requestWrapperFilter, threadLocalFilter);
			for (Filter filter : filters) {
				this.registerServletFilter(servletContext, filter);
			}
		}
	}

	private WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(RootConfig.class);
		context.refresh();// 在通过ApplicationContext访问Bean之前调用'refresh'方法，否则会抛BeanFactory未初始化或已关闭异常。
		log.info("DisplayName={}, BeanDefinitionCount={}", context.getDisplayName(), context.getBeanDefinitionCount());
		return context;
	}

	private WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(ServletConfig.class);
		return context;
	}

	private FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
		String filterName = Conventions.getVariableName(filter);
		FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);

		if (registration == null) {
			int counter = 0;
			while (registration == null) {
				if (counter == 100) {
					throw new IllegalStateException("无法注册过滤器 '" + filterName + "'. 检查是否有另一个过滤器以相同的名称注册.");
				}
				registration = servletContext.addFilter(filterName + "#" + counter, filter);
				counter++;
			}
		}

		registration.setAsyncSupported(DEFAULT_ASYNC_SUPPORTED);
		registration.addMappingForServletNames(this.getDispatcherTypes(), false, DEFAULT_SERVLET_NAME);
		return registration;
	}

	private EnumSet<DispatcherType> getDispatcherTypes() {
		return (DEFAULT_ASYNC_SUPPORTED
				? EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
						DispatcherType.ASYNC)
				: EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE));
	}

}
