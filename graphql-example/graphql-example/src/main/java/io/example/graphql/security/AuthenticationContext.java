package io.example.graphql.security;

import io.example.graphql.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationContext {
    private User user;
    private boolean tokenInvalid;

    public void ensureAuthenticated() {
        if (tokenInvalid) {
            throw new RuntimeException("令牌无效！");
        }
        if (user == null) {
            throw new RuntimeException("未登录，请先登录！");
        }
    }
}
