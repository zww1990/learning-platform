package io.example.dgs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import io.example.dgs.domain.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * Jwt Context
 *
 * @author zww
 * @since 2023-08-21 21:05:33
 */
public final class JwtContext {
    private static final String ISSUER = "java hero";
    private static final String USER_ID = "userId";
    private static final String USERNAME = "username";
    private static final String SECRET = "hello world";
    private static final long AMOUNT_TO_ADD = 1L;
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    private JwtContext() {
    }

    public static String createToken(User user) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim(USER_ID, user.getUserId())
                .withClaim(USERNAME, user.getUsername())
                .withExpiresAt(Instant.now().plus(AMOUNT_TO_ADD, ChronoUnit.HOURS))
                .sign(ALGORITHM);
    }

    public static User verifyToken(String token) {
        Map<String, Claim> claims = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getClaims();
        return new User()
                .setUserId(claims.get(USER_ID).asInt())
                .setUsername(claims.get(USERNAME).asString());
    }
}
