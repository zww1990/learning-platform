//package com.example.client;
//
//import javax.servlet.Filter;
//import javax.servlet.ServletContext;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
//import org.jasig.cas.client.util.AssertionThreadLocalFilter;
//import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
//import org.springframework.web.filter.DelegatingFilterProxy;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import com.example.client.config.RootConfig;
//import com.example.client.config.ServletConfig;
//
//public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return ArrayUtils.<Class<?>>toArray(RootConfig.class);
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return ArrayUtils.<Class<?>>toArray(ServletConfig.class);
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		return ArrayUtils.toArray("/");
//	}
//
//	@Override
//	protected Filter[] getServletFilters() {
//		// CAS中的Single Sign Out支持包括配置一个SingleSignOutFilter和一个ContextListener。
//		// 请注意，如果您已将CAS Client for Java配置为Web过滤器，则此过滤器必须位于其他过滤器之前。
//		DelegatingFilterProxy singleSignOutFilterProxy = new DelegatingFilterProxy("singleSignOutFilter");
//		// 通过Spring IoC的配置将在很大程度上取决于DelegatingFilterProxy类。
//		DelegatingFilterProxy authenticationFilterProxy = new DelegatingFilterProxy("authenticationFilter");
//		DelegatingFilterProxy ticketValidationFilterProxy = new DelegatingFilterProxy("ticketValidationFilter");
//		// 包装一个HttpServletRequest，以便getRemoteUser和getPrincipal返回CAS相关的条目。
//		HttpServletRequestWrapperFilter requestWrapperFilter = new HttpServletRequestWrapperFilter();
//		// 将断言置于ThreadLocal中，以便其他资源可以访问不具有Web层会话的权限。
//		AssertionThreadLocalFilter threadLocalFilter = new AssertionThreadLocalFilter();
//		return ArrayUtils.toArray(singleSignOutFilterProxy, authenticationFilterProxy, ticketValidationFilterProxy,
//				requestWrapperFilter, threadLocalFilter);
//	}
//
//	@Override
//	protected void registerContextLoaderListener(ServletContext servletContext) {
//		super.registerContextLoaderListener(servletContext);
//		// 侦听器，用于检测HTTP会话何时被销毁，并将其从管理会话的映射中删除。 还允许程序性地删除会话。启用CAS单一登出功能。
//		servletContext.addListener(new SingleSignOutHttpSessionListener());
//	}
//}
