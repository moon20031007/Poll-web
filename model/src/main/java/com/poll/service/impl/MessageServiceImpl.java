package com.poll.service.impl;

import com.poll.DTO.MessageInfoDTO;
import com.poll.mapper.MessageMapper;
import com.poll.mapper.UserMapper;
import com.poll.pojo.Message;
import com.poll.pojo.User;
import com.poll.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;
    private final UserMapper userMapper;

    public MessageServiceImpl(MessageMapper messageMapper, UserMapper userMapper) {
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void send(User sender, Message message) {
        message.setSenderId(sender.getUserId());
        messageMapper.send(message);
    }

    @Override
    public MessageInfoDTO getMessages(User user) {
        List<Message> messages = messageMapper.getMessages(user);
        MessageInfoDTO messageInfoDTO = new MessageInfoDTO();
        List<User> userList = new ArrayList<>();
        messages.forEach(message -> {
            if (message.getSenderId().equals(user.getUserId())) {
                User receiver = userMapper.selectById(message.getReceiverId());
                receiver.setPassword(null);
                userList.add(receiver);
            } else {
                User sender = userMapper.selectById(message.getSenderId());
                userList.add(sender);
            }
        });
        messageInfoDTO.setMessages(messages);
        messageInfoDTO.setCurrentUser(user);
        messageInfoDTO.setUserList(userList.stream().distinct().toList());
        return messageInfoDTO;
    }
}
