package io.online.videosite.domain;

import io.online.videosite.constant.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户实体类
 *
 * @author 张维维
 * @since 2023-10-02 12:39:13
 */
@Table(name = "t_user")
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户类型
     */
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
