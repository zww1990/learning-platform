package io.online.videosite.repository;

import io.online.videosite.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户数据访问对象接口
 *
 * @author 张维维
 * @since 2023-10-02 13:53:14
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
