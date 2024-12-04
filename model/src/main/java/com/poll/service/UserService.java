package com.poll.service;

import com.poll.pojo.User;

public interface UserService {

    User selectById(Integer id);

    User login(String email, String password);

    void registerStepOne(User user);

    Boolean registerStepTwo(User user, String verification);

    void passwordResetStepOne(User user);

    Boolean passwordResetStepTwo(User user, String password, String verification);

    String avatar(User user, String avatarName);

    void updateProfile(Integer id, String profile);

    void updateInfo(Integer id, User user);

    void enableOperate(User user);
}
