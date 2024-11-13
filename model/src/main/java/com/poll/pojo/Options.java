package com.poll.pojo;

import lombok.Data;

@Data
public class Options {
    private Integer optionId;

    private Integer pollId;

    private String description;

    private Integer order;
}
