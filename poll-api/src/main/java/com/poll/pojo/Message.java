package com.poll.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer messageId;

    private Integer senderId;

    private Integer receiverId;

    private String content;

    private Date time;

    private Boolean isRead;
}
