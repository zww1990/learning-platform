package io.example.graphql.service;

import io.example.graphql.domain.LoginInput;
import io.example.graphql.domain.LoginOutput;
import io.example.graphql.domain.User;
import io.example.graphql.security.JwtContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class UserService {
    private final List<User> userList = new CopyOnWriteArrayList<>();

    public User createUser(User user) {
        log.info("createUser(): user = {}", user);
        user.setUserId(userList.size() + 1);
        userList.add(user);
        return user;
    }

    public List<User> queryUsers() {
        log.info("queryUsers(): ");
        return userList;
    }

    public LoginOutput userLogin(LoginInput loginInput) {
        log.info("userLogin(): loginInput = {}", loginInput);
        return userList.stream()
                .filter(f ->
                        f.getUsername().equals(loginInput.getUsername()) &&
                                f.getPassword().equals(loginInput.getPassword()))
                .findFirst()
                .map(m -> new LoginOutput()
                        .setUserId(m.getUserId())
                        .setUsername(m.getUsername())
                        .setToken(JwtContext.createToken(m)))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NO_CONTENT, "用户名或密码不正确！"));
    }
}
