package io.example.graphql.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * File Info
 *
 * @author zww
 * @since 2023-08-23 10:40:20
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class FileInfo {
    private String id;
    private String name;
    private String contentType;
    private String originalFilename;
    private Long size;
}
