package io.example.kickstart.security;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Authentication Context Aspect
 *
 * @author zww
 * @since 2023-08-23 15:39:20
 */
@Aspect
@Component
@Slf4j
public class AuthenticationContextAspect {

    @Before("@annotation(io.example.kickstart.security.Authentication)")
    public void doAccessCheck(JoinPoint joinPoint) {
        log.info("doAccessCheck(): joinPoint = {}", joinPoint);
        try {
            AuthenticationContext.get().ensureAuthenticated();
        } finally {
            AuthenticationContext.clear();
            log.info("doAccessCheck(): AuthenticationContext = {}", AuthenticationContext.get());
        }
    }
}
