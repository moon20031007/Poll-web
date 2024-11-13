package com.poll.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Poll {
    private Integer pollId;

    private Integer creatorId;

    private Boolean type;

    private String title;

    private Integer starCount;

    private String description;

    private Date startTime;

    private Date endTime;

    private Boolean allowAnonymous;

    private Boolean enabled;
}
