package com.example.security.support;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.PathMatcher;

import com.example.security.model.Authority;
import com.example.security.model.Menu;
import com.example.security.service.MenuService;

import lombok.extern.slf4j.Slf4j;

/**
 * 安全元数据源实现标记接口，旨在执行过滤器调用上键对的查找。
 * 
 * @author home
 */
@Slf4j
public class DaoFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Resource
	private PathMatcher pathMatcher;
	@Resource
	private MenuService menuService;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if (requestUrl.startsWith("/login")) {
			return null;
		}
		List<Menu> menus = this.menuService.queryMenus();
		for (Menu menu : menus) {
			log.info("模式URL={}, 请求URL={}", menu.getPatternUrl(), requestUrl);
			if (this.pathMatcher.match(menu.getPatternUrl(), requestUrl)) {
				String[] names = menu.getAuthorities().stream().map(Authority::getAuthority).toArray(String[]::new);
				log.info("权限列表：{}", Arrays.toString(names));
				return SecurityConfig.createList(names);
			}
		}
		return SecurityConfig.createList("ROLE_LOGIN");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
