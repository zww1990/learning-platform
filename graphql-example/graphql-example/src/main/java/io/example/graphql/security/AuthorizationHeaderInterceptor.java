package io.example.graphql.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * Authorization Header Interceptor
 *
 * @author zww
 * @since 2023-08-21 21:05:57
 */
@Component
@Slf4j
public class AuthorizationHeaderInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        AuthenticationContext context = new AuthenticationContext();
        if (request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            String value = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasText(value) && value.startsWith("Bearer ")) {
                value = value.replace("Bearer ", "");
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
        request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(Collections.singletonMap("authenticationContext", context)).build());
        return chain.next(request);
    }
}
