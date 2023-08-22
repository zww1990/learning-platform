package io.example.graphql.controller;

import io.example.graphql.domain.LoginInput;
import io.example.graphql.domain.LoginOutput;
import io.example.graphql.domain.User;
import io.example.graphql.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @MutationMapping("createUser")
    public User createUser(@Argument User user) {
        return userService.createUser(user);
    }

    @QueryMapping("userList")
    public List<User> userList() {
        return userService.queryUsers();
    }

    @QueryMapping("login")
    public LoginOutput login(@Argument LoginInput loginInput) {
        return userService.userLogin(loginInput);
    }
}
