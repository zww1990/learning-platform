package io.example.reactive.config;

import io.example.reactive.service.UserService;
import io.example.reactive.service.UserServiceV2;
import io.example.reactive.model.ClientUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;

/**
 * 路由配置类
 *
 * @author zww
 * @since 2023-08-12 13:39:52
 */
@Configuration
public class RouterFunctionConfig {
    private final UserService userService;
    private final UserServiceV2 userServiceV2;

    public RouterFunctionConfig(UserService userService, UserServiceV2 userServiceV2) {
        this.userService = userService;
        this.userServiceV2 = userServiceV2;
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        RouterFunction<ServerResponse> rf1 = RouterFunctions.route()
                .path("/user", builder -> builder
                        .GET("/get", request -> ServerResponse.ok().body(userService.getClientUser(), ClientUser.class))
                        .POST("/add", request -> request.bodyToMono(ClientUser.class).flatMap(userService::addClientUser).flatMap(p -> ServerResponse.ok().bodyValue(p)))
                        .PUT("/update", request -> request.bodyToMono(ClientUser.class).flatMap(userService::updateClientUser).flatMap(p -> ServerResponse.ok().bodyValue(p)))
                        .DELETE("/del/{userId}", request -> ServerResponse.ok().body(userService.delClientUser(request.pathVariable("userId")), Long.class))
                        .GET("/get/{userId}", request -> ServerResponse.ok().body(userService.getClientUserByUserId(request.pathVariable("userId")), ClientUser.class))
                ).build();
        RouterFunction<ServerResponse> rf2 = RouterFunctions.route()
                .path("/v2/user", builder -> builder
                        .GET("/get", request -> ServerResponse.ok().body(userServiceV2.getClientUser(), ClientUser.class))
                        .POST("/add", request -> request.bodyToMono(ClientUser.class).flatMap(userServiceV2::addClientUser).flatMap(p -> ServerResponse.ok().bodyValue(p)))
                        .PUT("/update", request -> request.bodyToMono(ClientUser.class).flatMap(userServiceV2::updateClientUser).flatMap(p -> ServerResponse.ok().bodyValue(p)))
                        .DELETE("/del/{userId}", request -> ServerResponse.ok().body(userServiceV2.delClientUser(request.pathVariable("userId")), Long.class))
                        .GET("/get/{userId}", request -> ServerResponse.ok().body(userServiceV2.getClientUserByUserId(request.pathVariable("userId")), ClientUser.class))
                ).build();
        return RouterFunctions.route()
                .GET("/", request -> ServerResponse.ok().bodyValue(List.of("你好，", "世界！")))
                .add(rf1).add(rf2).build();
    }
}
