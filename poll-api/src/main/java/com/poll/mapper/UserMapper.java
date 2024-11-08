package com.poll.mapper;

import com.poll.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByEmail(String email);

    void insert(User user);

    User selectById(Integer id);

    void updateAvatar(Integer id, String avatar);

    void updateProfile(Integer id, String profile);
}
