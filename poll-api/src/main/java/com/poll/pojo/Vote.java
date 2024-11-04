package com.poll.pojo;

import lombok.Data;

@Data
public class Vote {
    private Integer voteId;

    private Integer voterId;

    private Integer pollId;

    private Integer optionId;

    private Boolean isAnonymous;
}
