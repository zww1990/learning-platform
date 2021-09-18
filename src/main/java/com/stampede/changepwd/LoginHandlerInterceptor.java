package com.stampede.changepwd;

import java.util.Base64;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.ldap.LdapProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangWeiWei
 * @date 2020年2月26日,下午8:31:37
 * @description 管理员登录拦截器
 */
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {
	@Resource
	private LdapProperties properties;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("请求地址>>>{}", request.getRequestURL());
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.hasText(authorization)) {
			String[] authorizations = authorization.split(" ");
			authorization = new String(Base64.getDecoder().decode(authorizations[1]));
			authorizations = authorization.split(":");
			if (authorizations.length == 2) {
				String username = authorizations[0];
				String password = authorizations[1];
				if (this.properties.getUsername().equals(username) //
						&& this.properties.getPassword().equals(password)) {
					request.getSession().setAttribute("admin", username);
					log.info("管理员[{}]登录成功！", username);
					return true;
				}
			}
		}
		log.info("管理员登录失败！");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setHeader(HttpHeaders.WWW_AUTHENTICATE,
				String.format("Basic Realm=\"%s\"", HttpStatus.UNAUTHORIZED.getReasonPhrase()));
		return false;
	}
}
