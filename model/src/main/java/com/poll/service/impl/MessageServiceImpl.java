package com.poll.service.impl;

import com.poll.mapper.MessageMapper;
import com.poll.pojo.Message;
import com.poll.pojo.User;
import com.poll.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public void send(User sender, Message message) {
        message.setSenderId(sender.getUserId());
        messageMapper.send(message);
    }

    @Override
    public List<Message> getMessages(User user) {
        return messageMapper.getMessages(user);
    }
}
