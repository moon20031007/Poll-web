package com.poll.mapper;

import com.poll.pojo.Message;
import com.poll.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    void send(Message message);

    List<Message> getMessages(User user);
}
