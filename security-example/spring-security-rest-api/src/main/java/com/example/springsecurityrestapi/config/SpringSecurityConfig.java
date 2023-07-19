package com.example.springsecurityrestapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.springsecurityrestapi.authprovider.RequestHeaderAuthenticationProvider;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Spring安全框架配置
 * 
 * @author zhang weiwei
 * @since 2023年7月19日,下午1:42:01
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Autowired
	private RequestHeaderAuthenticationProvider requestHeaderAuthenticationProvider;
	@Autowired
	private ApiAuthProperties apiAuth;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity//
				.cors(Customizer.withDefaults())//
				.csrf(csrf -> csrf.disable())//
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterAfter(requestHeaderAuthenticationFilter(), HeaderWriterFilter.class)
				.authorizeHttpRequests(request -> request.requestMatchers("/api/**").authenticated())
				.exceptionHandling(handling -> handling.authenticationEntryPoint(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)));
		return httpSecurity.build();
	}

	@Bean
	RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() {
		RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
		filter.setPrincipalRequestHeader(apiAuth.getHeaderName());
		filter.setExceptionIfHeaderMissing(false);
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/**"));
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

	@Bean
	AuthenticationManager authenticationManager() {
		return new ProviderManager(requestHeaderAuthenticationProvider);
	}
}
