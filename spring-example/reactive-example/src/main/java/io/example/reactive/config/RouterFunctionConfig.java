package io.example.reactive.config;

import io.example.reactive.controller.UserController;
import io.example.reactive.controller.UserControllerV2;
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
    private final UserController userController;
    private final UserControllerV2 userControllerV2;

    public RouterFunctionConfig(UserController userController, UserControllerV2 userControllerV2) {
        this.userController = userController;
        this.userControllerV2 = userControllerV2;
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        RouterFunction<ServerResponse> a = RouterFunctions.route()
                .path("/user", builder -> builder
                        .GET("/get", request -> ServerResponse.ok().body(userController.getClientUser(), ClientUser.class))
                        .POST("/add", request -> request.bodyToMono(ClientUser.class).flatMap(userController::addClientUser).flatMap(p -> ServerResponse.ok().bodyValue(p)))
                        .PUT("/update", request -> request.bodyToMono(ClientUser.class).flatMap(userController::updateClientUser).flatMap(p -> ServerResponse.ok().bodyValue(p)))
                        .DELETE("/del/{userId}", request -> ServerResponse.ok().body(userController.delClientUser(request.pathVariable("userId")), Long.class))
                        .GET("/get/{userId}", request -> ServerResponse.ok().body(userController.getClientUserByUserId(request.pathVariable("userId")), ClientUser.class))
                ).build();
        RouterFunction<ServerResponse> b = RouterFunctions.route()
                .path("/v2/user", builder -> builder
                        .GET("/get", request -> ServerResponse.ok().body(userControllerV2.getClientUser(), ClientUser.class))
                        .POST("/add", request -> request.bodyToMono(ClientUser.class).flatMap(userControllerV2::addClientUser).flatMap(p -> ServerResponse.ok().bodyValue(p)))
                        .PUT("/update", request -> request.bodyToMono(ClientUser.class).flatMap(userControllerV2::updateClientUser).flatMap(p -> ServerResponse.ok().bodyValue(p)))
                        .DELETE("/del/{userId}", request -> ServerResponse.ok().body(userControllerV2.delClientUser(request.pathVariable("userId")), Long.class))
                        .GET("/get/{userId}", request -> ServerResponse.ok().body(userControllerV2.getClientUserByUserId(request.pathVariable("userId")), ClientUser.class))
                ).build();
        return RouterFunctions.route()
                .GET("/", request -> ServerResponse.ok().bodyValue(List.of("你好，", "世界！")))
                .add(a).add(b).build();
    }
}
