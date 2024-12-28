package com.poll.mapper;

import com.poll.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    void enableOperate(Integer id);

    List<User> selectAll(Integer offset, Integer size);

    Integer getAllPollCount();

    List<User> search(String keyword);
}
