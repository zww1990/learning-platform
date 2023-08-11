package io.example.reactive;

import java.util.List;

import io.example.reactive.model.ClientUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.example.reactive.controller.UserController;

import lombok.extern.slf4j.Slf4j;

/**
 * 应用程序启动类
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午5:53:23
 */
@SpringBootApplication
@Slf4j
public class ReactiveExampleApplication {
	private final UserController userController;

	public ReactiveExampleApplication(UserController userController) {
		this.userController = userController;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ReactiveExampleApplication.class, args);
		log.info("工厂中定义的 bean 数量 = {}", context.getBeanDefinitionCount());
//		java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	/**
	 * 定义路由
	 * 
	 * @author zhang weiwei
	 * @since 2023年8月3日,下午1:53:20
	 * @return {@link RouterFunction}
	 */
	@Bean
	RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route()//
				.GET("/", request -> ServerResponse.ok().bodyValue(List.of("你好，", "世界！")))//
				.GET("/user/get", request -> ServerResponse.ok().body(userController.getClientUser(), ClientUser.class))
				.POST("/user/add", request -> request.bodyToMono(ClientUser.class)//
						.flatMap(userController::addClientUser)//
						.flatMap(p -> ServerResponse.ok().bodyValue(p)))
				.PUT("/user/update", request -> request.bodyToMono(ClientUser.class)//
						.flatMap(userController::updateClientUser)//
						.flatMap(p -> ServerResponse.ok().bodyValue(p)))
				.DELETE("/user/del/{userId}",
						request -> ServerResponse.ok()
								.body(userController.delClientUser(request.pathVariable("userId")), Integer.class))
				.GET("/user/get/{userId}",
						request -> ServerResponse.ok().body(
								userController.getClientUserByUserId(request.pathVariable("userId")), ClientUser.class))
				.build();
	}
}
