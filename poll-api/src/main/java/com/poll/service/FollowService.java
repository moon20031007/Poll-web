package com.poll.service;

import com.poll.pojo.User;

public interface FollowService {

    void followOperate(User follower, User following);
}
