package com.poll.mapper;

import com.poll.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByEmail(String email);

    void insert(User user);

    void updatePassword(User user);

    User selectById(Integer id);

    User selectByUsername(String username);

    void updateAvatar(Integer id, String avatar);

    void updateProfile(Integer id, String profile);

    void updateInfo(Integer id, User user);

    void operateEnable(Integer id);
}
