package io.example.reactive.config;

import io.example.reactive.service.UserServiceV3;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class RouterFunctionConfig {
    private final UserServiceV3 userServiceV3;

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        RouterFunction<ServerResponse> rf = RouterFunctions.route()
                .path("/v2/user", builder -> builder
                        .GET("/get", this.userServiceV3::getClientUser)
                        .POST("/add", this.userServiceV3::addClientUser)
                        .PUT("/update", this.userServiceV3::updateClientUser)
                        .DELETE("/del/{userId}", this.userServiceV3::delClientUser)
                        .GET("/get/{userId}", this.userServiceV3::getClientUserByUserId)
                ).build();
        return RouterFunctions.route()
                .GET("/", request -> ServerResponse.ok().bodyValue(List.of("你好，", "世界！")))
                .add(rf).build();
    }
}
