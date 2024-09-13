package io.example.reactive.service;

import io.example.reactive.model.ClientUser;
import io.example.reactive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 用户服务实现
 *
 * @author weiwei
 * @version v3
 * @since 2022年4月26日, 下午4:14:03
 */
@Service
@AllArgsConstructor
public class UserServiceV3 {
    private final UserRepository userRepository;

    /**
     * 查询所有用户，并按userId排序
     *
     * @return 多个用户
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:24:12
     */
    public Mono<ServerResponse> getClientUser(ServerRequest request) {
        return ServerResponse.ok()
                .body(this.userRepository.findAll(Sort.by("userId")), ClientUser.class);
    }

    /**
     * 添加用户
     *
     * @param request 用户
     * @return 单个用户
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:36:02
     */
    public Mono<ServerResponse> addClientUser(ServerRequest request) {
        return ServerResponse.ok()
                .body(this.userRepository.saveAll(request.bodyToMono(ClientUser.class)), ClientUser.class);
    }

    /**
     * 按userId删除用户
     *
     * @param request 用户唯一标识
     * @return 影响行数
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:37:27
     */
    public Mono<ServerResponse> delClientUser(ServerRequest request) {
        return ServerResponse.ok()
                .body(this.userRepository.deleteByUserId(request.pathVariable("userId")), Long.class);
    }

    /**
     * 按userId查询用户
     *
     * @param request 用户唯一标识
     * @return 单个用户
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:39:56
     */
    public Mono<ServerResponse> getClientUserByUserId(ServerRequest request) {
        return ServerResponse.ok()
                .body(this.userRepository.findOne(Example.of(new ClientUser()
                        .setUserId(request.pathVariable("userId")))), ClientUser.class);
    }

    /**
     * 更新用户
     *
     * @param request 用户
     * @return 单个用户
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:41:03
     */
    public Mono<ServerResponse> updateClientUser(ServerRequest request) {
        return ServerResponse.ok()
                .body(this.userRepository.saveAll(request.bodyToMono(ClientUser.class)), ClientUser.class);
    }
}
