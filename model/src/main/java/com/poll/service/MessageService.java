package com.poll.service;

import com.poll.DTO.MessageInfoDTO;
import com.poll.pojo.Message;
import com.poll.pojo.User;

public interface MessageService {

    void send(User sender, Message message);

    MessageInfoDTO getMessages(User user);
}
