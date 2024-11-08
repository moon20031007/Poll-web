package com.poll.service;

import com.poll.pojo.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User login(String email, String password);

    void registerStepOne(User user);

    Boolean registerStepTwo(User user, String verification);

    Boolean avatar(User user, MultipartFile avatar);

    void updateProfile(Integer id, String profile);
}
