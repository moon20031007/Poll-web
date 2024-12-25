package com.poll.service.impl;

import com.poll.DTO.MessageInfoDTO;
import com.poll.mapper.MessageMapper;
import com.poll.mapper.UserMapper;
import com.poll.pojo.Message;
import com.poll.pojo.User;
import com.poll.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Map<User, Integer> userList = new HashMap<>();
        messages.forEach(message -> {
            Integer counterpartUserId = message.getSenderId().equals(user.getUserId()) ? message.getReceiverId() : message.getSenderId();
            User counterpartUser = userList.keySet().stream().filter(u -> u.getUserId().equals(counterpartUserId)).findFirst().orElse(null);
            if (counterpartUser == null) {
                counterpartUser = userMapper.selectById(counterpartUserId);
                counterpartUser.setPassword(null);
                if (!message.getIsRead() && message.getReceiverId().equals(user.getUserId())) {
                    userList.put(counterpartUser, 1);
                } else {
                    userList.put(counterpartUser, 0);
                }
            } else {
                if (!message.getIsRead() && message.getReceiverId().equals(user.getUserId())) {
                    userList.merge(counterpartUser, 1, Integer::sum);
                }
            }
            if (message.getSenderId().equals(user.getUserId())) {
                message.setIsRead(null);
            }
        });
        messageInfoDTO.setMessages(messages);
        messageInfoDTO.setCurrentUser(user);
        messageInfoDTO.setUserList(userList);
        return messageInfoDTO;
    }

    @Override
    public void read(User currentUser, User user) {
        messageMapper.read(currentUser.getUserId(), user.getUserId());
    }

    @Override
    public Integer getUnreadMessages(User user) {
        return messageMapper.getUnreadMessages(user.getUserId());
    }
}
