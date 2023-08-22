package io.example.graphql.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Login Output
 *
 * @author zww
 * @since 2023-08-22 16:42:16
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class LoginOutput {
    private Integer userId;
    private String username;
    private String token;
}
