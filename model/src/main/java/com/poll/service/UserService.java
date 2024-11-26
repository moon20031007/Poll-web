package com.poll.service;

import com.poll.pojo.User;

public interface UserService {

    User login(String email, String password);

    void registerStepOne(User user);

    Boolean registerStepTwo(User user, String verification);

    String avatar(User user, String avatarName);

    void updateProfile(Integer id, String profile);

    void enableOperate(User user);
}
