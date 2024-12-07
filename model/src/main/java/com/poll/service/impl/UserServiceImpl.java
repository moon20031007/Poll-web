package com.poll.service.impl;

import com.poll.DTO.UserInfoDTO;
import com.poll.mapper.*;
import com.poll.pojo.*;
import com.poll.service.EmailService;
import com.poll.service.UserService;
import com.poll.utils.HashUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PollMapper pollMapper;
    private final VoteMapper voteMapper;
    private final OptionsMapper optionsMapper;
    private final FollowMapper followMapper;
    private final EmailService emailService;
    private final StringRedisTemplate redisTemplate;

    public UserServiceImpl(UserMapper userMapper, PollMapper pollMapper, VoteMapper voteMapper, OptionsMapper optionsMapper, FollowMapper followMapper, EmailService emailService, StringRedisTemplate redisTemplate) {
        this.userMapper = userMapper;
        this.pollMapper = pollMapper;
        this.voteMapper = voteMapper;
        this.optionsMapper = optionsMapper;
        this.followMapper = followMapper;
        this.emailService = emailService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public UserInfoDTO selectUserInfo(String username) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return null;
        }
        user.setPassword(null);
        List<Poll> posts = pollMapper.selectPost(user.getUserId());
        List<Map<Poll, Options>> map = new ArrayList<>();
        List<Vote> votes = voteMapper.selectByUserId(user.getUserId());
        List<Poll> stars = pollMapper.selectStar(user.getUserId());
        List<Follow> follows = followMapper.selectByUserId(user.getUserId());
        List<User> followings = new ArrayList<>();
        List<User> followers = new ArrayList<>();
        follows.forEach(follow -> {
            if (follow.getFollowerId().equals(user.getUserId())) {
                User following = userMapper.selectById(follow.getFollowingId());
                following.setPassword(null);
                following.setIsAdmin(null);
                followings.add(following);
            } else {
                User follower = userMapper.selectById(follow.getFollowerId());
                follower.setPassword(null);
                follower.setIsAdmin(null);
                followers.add(follower);
            }
        });
        votes.forEach(vote -> {
            Poll poll = pollMapper.selectById(vote.getPollId());
            Options option = optionsMapper.selectById(vote.getOptionId());
            map.add(new HashMap<Poll, Options>() {{
                put(poll, option);
            }});
        });
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUser(user);
        userInfoDTO.setPosts(posts);
        userInfoDTO.setVotes(map);
        userInfoDTO.setStars(stars);
        userInfoDTO.setFollowings(followings);
        userInfoDTO.setFollowers(followers);
        return userInfoDTO;
    }

    @Override
    public User login(String email) {
        return userMapper.selectByEmail(email);
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

    @Override
    public String passwordReset(User user, String verification) {
        String storedVerificationCode = redisTemplate.opsForValue().get(user.getEmail());
        if (storedVerificationCode == null || !verification.equals(storedVerificationCode)) {
            return "verification error";
        }
        user.setPassword(HashUtils.encodePassword(user.getPassword()));
        userMapper.updatePassword(user);
        redisTemplate.delete(user.getEmail());
        return null;
    }

    @Override
    public void passwordResetStepOne(User user) {
        emailService.send(user);
    }

    @Override
    public Boolean passwordResetStepTwo(User user, String password, String verification) {
        String storedVerificationCode = redisTemplate.opsForValue().get(user.getEmail());
        if (storedVerificationCode == null || !verification.equals(storedVerificationCode)) {
            return false;
        }
        user.setPassword(HashUtils.encodePassword(password));
        userMapper.updatePassword(user);
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

    @Override
    public void updateInfo(Integer id, User user) {
        userMapper.updateInfo(id, user);
    }

    @Override
    public void enableOperate(User user) {
        userMapper.operateEnable(user.getUserId());
    }
}
