package io.example.kickstart.security;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.kickstart.servlet.core.GraphQLServletListener;
import graphql.kickstart.spring.error.ErrorContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Authentication Context Builder
 *
 * @author zww
 * @since 2023-08-24 17:45:01
 */
@Slf4j
@ControllerAdvice
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
            log.debug("用户未认证！");
        }
        AuthenticationContext.set(context);
        return GraphQLServletListener.super.onRequest(request, response);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public GraphQLError handle(HttpClientErrorException ex, ErrorContext context) {
        ErrorClassification errorType = AuthorizationErrorType.INTERNAL_ERROR;
        if (ex.getStatusCode() instanceof HttpStatus status) {
            errorType = switch (status) {
                case BAD_REQUEST -> AuthorizationErrorType.BAD_REQUEST;
                case UNAUTHORIZED -> AuthorizationErrorType.UNAUTHORIZED;
                case FORBIDDEN -> AuthorizationErrorType.FORBIDDEN;
                case NOT_FOUND -> AuthorizationErrorType.NOT_FOUND;
                case NO_CONTENT -> AuthorizationErrorType.NO_CONTENT;
                default -> AuthorizationErrorType.INTERNAL_ERROR;
            };
        }
        return GraphQLError.newError()
                .errorType(errorType)
                .message(ex.getLocalizedMessage())
                .path(context.getPath())
                .locations(context.getLocations())
                .build();
    }

    enum AuthorizationErrorType implements ErrorClassification {
        // 204 No Content.
        NO_CONTENT,
        // 500 Internal Server Error.
        INTERNAL_ERROR,
        // 400 Bad Request.
        BAD_REQUEST,
        // 401 Unauthorized.
        UNAUTHORIZED,
        // 403 Forbidden.
        FORBIDDEN,
        // 404 Not Found.
        NOT_FOUND
    }
}
