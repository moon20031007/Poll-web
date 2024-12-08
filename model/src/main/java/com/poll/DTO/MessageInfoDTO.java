package com.poll.DTO;

import com.poll.pojo.Message;
import com.poll.pojo.User;
import lombok.Data;

import java.util.List;

@Data
public class MessageInfoDTO {

    private List<Message> messages;

    private User currentUser;

    private List<User> userList;
}
