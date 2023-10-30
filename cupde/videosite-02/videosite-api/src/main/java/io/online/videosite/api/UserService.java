package io.online.videosite.api;

import io.online.videosite.domain.User;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author 张维维
 * @since 2023-10-02 14:15:46
 */
public interface UserService {
    /**
     * 按用户名查询用户信息
     *
     * @param user {@link User}
     * @return {@link User}
     * @author 张维维
     * @since 2023-10-03 20:16:51
     */
    User query(User user);

    /**
     * 添加新用户
     *
     * @param user {@link User}
     * @author 张维维
     * @since 2023-10-03 22:26:25
     */
    void save(User user);

    /**
     * 查询所有用户
     *
     * @return {@link List<User>}
     * @author 张维维
     * @since 2023-10-30 20:29:07
     */
    List<User> queryUsers();
}
