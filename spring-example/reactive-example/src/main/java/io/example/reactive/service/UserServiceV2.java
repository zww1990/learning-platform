package io.example.reactive.service;

import io.example.reactive.model.ClientUser;
import io.example.reactive.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 用户服务实现
 *
 * @author weiwei
 * @version v2
 * @since 2022年4月26日, 下午4:14:03
 */
@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 查询所有用户，并按userId排序
     *
     * @return 多个用户
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:24:12
     */
    public Flux<ClientUser> getClientUser() {
        return this.userRepository.findAll(Sort.by("userId"));
    }

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 单个用户
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:36:02
     */
    public Mono<ClientUser> addClientUser(ClientUser user) {
        return this.userRepository.save(user);
    }

    /**
     * 按userId删除用户
     *
     * @param userId 用户唯一标识
     * @return 影响行数
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:37:27
     */
    public Mono<Long> delClientUser(String userId) {
        return this.userRepository.deleteByUserId(userId);
    }

    /**
     * 按userId查询用户
     *
     * @param userId 用户唯一标识
     * @return 单个用户
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:39:56
     */
    public Mono<ClientUser> getClientUserByUserId(String userId) {
        return this.userRepository.findOne(Example.of(new ClientUser().setUserId(userId)));
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 单个用户
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:41:03
     */
    public Mono<ClientUser> updateClientUser(ClientUser user) {
        return this.userRepository.save(user);
    }
}
