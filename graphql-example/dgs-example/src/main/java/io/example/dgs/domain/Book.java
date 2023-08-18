package io.example.dgs.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Book
 *
 * @author zww
 * @since 2023-08-14 18:05:32
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Book {
    private Long id;
    private String name;
    private Integer pageCount;
    private List<Author> authors;
}
