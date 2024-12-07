package io.example.ximalaya;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Player {
    private Integer index;
    private Integer trackId;
    private String trackName;
    private String fileName;
    private String fileExt;
}
