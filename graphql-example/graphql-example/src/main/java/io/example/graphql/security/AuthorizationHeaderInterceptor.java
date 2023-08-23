package io.example.graphql.security;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * Authorization Header Interceptor
 *
 * @author zww
 * @since 2023-08-21 21:05:57
 */
@ControllerAdvice
@Slf4j
public class AuthorizationHeaderInterceptor implements WebGraphQlInterceptor {
    private static final String BEARER = "Bearer ";

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        AuthenticationContext context = new AuthenticationContext();
        if (request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            String value = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasText(value) && value.startsWith(BEARER)) {
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
        request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(Collections.singletonMap("authenticationContext", context)).build());
        AuthenticationContext.set(context);
        return chain.next(request);
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(HttpClientErrorException ex, DataFetchingEnvironment env) {
        ErrorClassification errorType = ErrorType.INTERNAL_ERROR;
        if (ex.getStatusCode() instanceof HttpStatus status) {
            errorType = switch (status) {
                case BAD_REQUEST -> ErrorType.BAD_REQUEST;
                case UNAUTHORIZED -> ErrorType.UNAUTHORIZED;
                case FORBIDDEN -> ErrorType.FORBIDDEN;
                case NOT_FOUND -> ErrorType.NOT_FOUND;
                case NO_CONTENT -> AuthorizationErrorType.NO_CONTENT;
                default -> ErrorType.INTERNAL_ERROR;
            };
        }
        return GraphQLError.newError()
                .errorType(errorType)
                .message(ex.getLocalizedMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }

    enum AuthorizationErrorType implements ErrorClassification {
        NO_CONTENT
    }
}
