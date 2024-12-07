package com.poll.service;

import com.poll.pojo.Message;
import com.poll.pojo.User;

import java.util.List;

public interface MessageService {

    void send(User sender, Message message);

    List<Message> getMessages(User user);
}
