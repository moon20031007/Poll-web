package com.poll.pojo;

import lombok.Data;

@Data
public class Topic {
    private Integer topicId;

    private String name;

    private String description;

    private Integer count;
}
