package io.example.dgs.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import io.example.dgs.domain.LoginInput;
import io.example.dgs.domain.LoginOutput;
import io.example.dgs.domain.User;
import io.example.dgs.service.UserService;
import lombok.AllArgsConstructor;

import java.util.List;

@DgsComponent
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @DgsMutation(field = "createUser")
    public User createUser(@InputArgument User user) {
        return userService.createUser(user);
    }

    @DgsQuery(field = "userList")
    public List<User> userList() {
        return userService.queryUsers();
    }

    @DgsQuery(field = "login")
    public LoginOutput login(@InputArgument LoginInput loginInput) {
        return userService.userLogin(loginInput);
    }
}
