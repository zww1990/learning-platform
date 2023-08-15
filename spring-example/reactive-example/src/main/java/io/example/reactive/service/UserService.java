package io.example.reactive.service;

import io.example.reactive.model.ClientUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 用户服务实现
 *
 * @author weiwei
 * @version v1
 * @since 2022年4月26日, 下午4:14:03
 */
@Service
@AllArgsConstructor
public class UserService {
    private final R2dbcEntityTemplate entityTemplate;

    /**
     * 查询所有用户，并按userId排序
     *
     * @return 多个用户
     * @author zhang weiwei
     * @since 2023年8月3日, 下午1:24:12
     */
    public Flux<ClientUser> getClientUser() {
        return this.entityTemplate.select(ClientUser.class)//
                .matching(Query.empty().sort(Sort.by("userId")))//
                .all();
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
        return this.entityTemplate.insert(user);
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
        return this.entityTemplate.delete(ClientUser.class)//
                .matching(Query.query(Criteria.where("userId").is(userId)))//
                .all();
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
        return this.entityTemplate.selectOne(//
                Query.query(Criteria.where("userId").is(userId)), //
                ClientUser.class);
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
        return this.entityTemplate.update(user);
    }
}
