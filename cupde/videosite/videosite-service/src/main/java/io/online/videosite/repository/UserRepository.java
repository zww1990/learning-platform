package io.online.videosite.repository;

import io.online.videosite.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 用户数据访问对象接口
 *
 * @author 张维维
 * @since 2023-10-02 13:53:14
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    /**
     * 按用户名查询用户信息
     *
     * @param username 用户名
     * @return {@link User}
     * @author 张维维
     * @since 2023-10-03 20:33:53
     */
    default Optional<User> findByUsername(String username) {
        return this.findOne((root, query, builder) -> builder.equal(root.get("username"), username));
    }
}
