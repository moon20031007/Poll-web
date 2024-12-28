package com.poll.service.impl;

import com.poll.mapper.FollowMapper;
import com.poll.pojo.Follow;
import com.poll.pojo.User;
import com.poll.service.FollowService;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowMapper followMapper;

    public FollowServiceImpl(FollowMapper followMapper) {
        this.followMapper = followMapper;
    }

    @Override
    public void followOperate(User follower, User following) {
        Follow isFollowed = followMapper.select(follower.getUserId(), following.getUserId());
        if (isFollowed == null) {
            followMapper.insert(follower.getUserId(), following.getUserId());
        } else {
            followMapper.delete(isFollowed.getFollowId());
        }
    }

    @Override
    public Follow select(User follower, Integer following) {
        return followMapper.select(follower.getUserId(), following);
    }
}
