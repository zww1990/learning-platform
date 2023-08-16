package io.example.graphql.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Author
 *
 * @author zww
 * @since 2023-08-14 18:05:42
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Author {
    private String id;
    private String firstName;
    private String lastName;
}
