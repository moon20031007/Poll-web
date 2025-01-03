package com.poll.service;

import com.poll.DTO.UserInfoDTO;
import com.poll.pojo.User;

import java.util.List;

public interface UserService {

    UserInfoDTO selectUserInfo(String username);

    User login(String email);

    void registerStepOne(User user);

    Boolean registerStepTwo(User user, String verification);

    String passwordReset(User user, String verification);

    void passwordResetStepOne(User user);

    Boolean passwordResetStepTwo(User user, String password, String verification);

    String avatar(User user, String avatarName);

    void updateProfile(Integer id, String profile);

    void enableOperate(User user);

    List<User> getAll(Integer page, Integer size);

    Integer getAllPageSize(Integer size);

    List<User> search(String keyword);
}
