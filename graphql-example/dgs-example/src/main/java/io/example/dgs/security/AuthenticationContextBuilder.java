package io.example.dgs.security;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import com.netflix.graphql.types.errors.ErrorType;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Authentication Context Builder
 *
 * @author zww
 * @since 2023-08-24 17:45:01
 */
@Slf4j
@Component
public class AuthenticationContextBuilder implements DgsCustomContextBuilderWithRequest<AuthenticationContext>, DataFetcherExceptionHandler {
    private static final String BEARER = "Bearer ";

    @Override
    public AuthenticationContext build(Map<String, ?> map, HttpHeaders httpHeaders, WebRequest webRequest) {
        AuthenticationContext context = new AuthenticationContext();
        if (httpHeaders != null && httpHeaders.containsKey(HttpHeaders.AUTHORIZATION)) {
            String value = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
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
            log.debug("用户未认证！");
        }
        AuthenticationContext.set(context);
        return context;
    }

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        ErrorClassification errorType = ErrorType.INTERNAL;
        if (handlerParameters.getException() instanceof HttpClientErrorException ex) {
            if (ex.getStatusCode() instanceof HttpStatus status) {
                errorType = switch (status) {
                    case BAD_REQUEST -> ErrorType.BAD_REQUEST;
                    case UNAUTHORIZED -> ErrorType.UNAUTHENTICATED;
                    case FORBIDDEN -> ErrorType.PERMISSION_DENIED;
                    case NOT_FOUND -> ErrorType.NOT_FOUND;
                    case NO_CONTENT -> AuthorizationErrorType.NO_CONTENT;
                    default -> ErrorType.INTERNAL;
                };
            }
            return CompletableFuture.completedFuture(DataFetcherExceptionHandlerResult.newResult()
                    .error(GraphQLError.newError()
                            .errorType(errorType)
                            .message(ex.getLocalizedMessage())
                            .path(handlerParameters.getPath())
                            .location(handlerParameters.getSourceLocation())
                            .build())
                    .build());
        }
        return DataFetcherExceptionHandler.super.handleException(handlerParameters);
    }

    enum AuthorizationErrorType implements ErrorClassification {
        NO_CONTENT
    }
}
