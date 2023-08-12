package io.example.reactive.repository;

import io.example.reactive.model.ClientUser;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

/**
 * 用户数据访问对象接口
 *
 * @author zww
 * @since 2023-08-12 13:37:46
 */
public interface UserRepository extends R2dbcRepository<ClientUser, Integer> {
    /**
     * 按userId删除用户
     *
     * @param userId 用户唯一标识
     * @return 影响行数
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:37:27
     */
    Mono<Long> deleteByUserId(String userId);
}
