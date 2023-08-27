package io.example.kickstart.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Login Input
 *
 * @author zww
 * @since 2023-08-22 16:42:00
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class LoginInput {
    private String username;
    private String password;
}
