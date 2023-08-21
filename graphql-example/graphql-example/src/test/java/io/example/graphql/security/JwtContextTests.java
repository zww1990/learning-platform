package io.example.graphql.security;

import io.example.graphql.domain.User;
import org.junit.jupiter.api.Test;

public class JwtContextTests {

    @Test
    public void testCreateToken() {
        System.out.println(JwtContext.createToken(new User()
                .setUserId(1001)
                .setUserName("张三")));
    }

    @Test
    public void testVerifyToken() {
        String s = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJqYXZhIGhlcm8iLCJ1c2VySWQiOjEwMDEsInVzZXJOYW1lIjoi5byg5LiJIiwiZXhwIjoxNjkyNjI3MjI1fQ.wvQ5SRWzVlXWf8TtHaAEKhNUJjiZgGsgKhXrJEL284Y";
        System.out.println(JwtContext.verifyToken(s));
    }
}
