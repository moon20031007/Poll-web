package com.poll.mapper;

import com.poll.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByEmail(String email);

    void insert(User user);
}
