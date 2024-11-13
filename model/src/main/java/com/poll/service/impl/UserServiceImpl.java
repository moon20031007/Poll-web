package com.poll.service.impl;

import com.poll.mapper.UserMapper;
import com.poll.pojo.User;
import com.poll.service.EmailService;
import com.poll.service.UserService;
import com.poll.utils.HashUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final EmailService emailService;
    private final StringRedisTemplate redisTemplate;

    public UserServiceImpl(UserMapper userMapper, EmailService emailService, StringRedisTemplate redisTemplate) {
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User login(String email, String password) {
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            return null;
        } else if (!user.getEnabled()) {
            return null;
        }
        if (HashUtils.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public void registerStepOne(User user) {
        emailService.send(user);
    }

    @Override
    public Boolean registerStepTwo(User user, String verification) {
        String storedVerificationCode = redisTemplate.opsForValue().get(user.getEmail());
        if (storedVerificationCode == null || !verification.equals(storedVerificationCode)) {
            return false;
        }
        user.setPassword(HashUtils.encodePassword(user.getPassword()));
        userMapper.insert(user);
        redisTemplate.delete(user.getEmail());
        return true;
    }

//    @Override
//    public Boolean avatar(User user, MultipartFile avatar) {
//        try {
//            User userToUpdate = userMapper.selectById(user.getUserId());
//            String avatarName = SaveImageUtils.saveImage(avatar, "avatar");
//            userMapper.updateAvatar(user.getUserId(), avatarName);
//            if (!Objects.equals(userToUpdate.getAvatar(), "default.png")){
//                SaveImageUtils.deleteImage(userToUpdate.getAvatar(), "avatar");
//            }
//            return true;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public String avatar(User user, String avatarName) {
        User userToUpdate = userMapper.selectById(user.getUserId());
        userMapper.updateAvatar(user.getUserId(), avatarName);
        return userToUpdate.getAvatar();
    }

    @Override
    public void updateProfile(Integer id, String profile) {
        userMapper.updateProfile(id, profile);
    }
}
