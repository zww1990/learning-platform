package io.example.dgs.security;

import io.example.dgs.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Authentication Context
 *
 * @author zww
 * @since 2023-08-22 14:19:14
 */
@Getter
@Setter
public class AuthenticationContext {
    private static final ThreadLocal<AuthenticationContext> THREAD_LOCAL = new ThreadLocal<>();
    private User user;
    private boolean tokenInvalid;

    public void ensureAuthenticated() {
        if (tokenInvalid) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "令牌无效！");
        }
        if (user == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "未登录，请先登录！");
        }
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }

    public static void set(AuthenticationContext context) {
        THREAD_LOCAL.set(context);
    }

    public static AuthenticationContext get() {
        return THREAD_LOCAL.get();
    }
}
