package com.poll.pojo;

import lombok.Data;

@Data
public class Image {
    private Integer imageId;

    private Integer pollId;

    private Integer order;

    private String path;
}
