package io.example.kickstart.security;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * Authentication Context Builder
 *
 * @author zww
 * @since 2023-08-24 17:45:01
 */
@Slf4j
@Component
public class AuthenticationContextBuilder implements GraphQLServletListener {
    private static final String BEARER = "Bearer ";

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        AuthenticationContext context = new AuthenticationContext();
        String value = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (value != null) {
            if (!value.isBlank() && value.startsWith(BEARER)) {
                value = value.replace(BEARER, "");
                log.info("JWT token = {}", value);
                try {
                    context.setUser(JwtContext.verifyToken(value));
                    log.info("用户认证成功。 {}", context.getUser());
                } catch (Exception e) {
                    log.error(e.getLocalizedMessage());
                    context.setTokenInvalid(true);
                }
            } else {
                log.error("{} 值无效！", HttpHeaders.AUTHORIZATION);
                context.setTokenInvalid(true);
            }
        } else {
            log.error("用户未认证！");
        }
        AuthenticationContext.set(context);
        return GraphQLServletListener.super.onRequest(request, response);
    }

}
