package io.example.dgs.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * User
 *
 * @author zww
 * @since 2023-08-21 21:05:45
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String email;
}
