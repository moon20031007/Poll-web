package com.poll.service;

import com.poll.pojo.Follow;
import com.poll.pojo.User;

public interface FollowService {

    void followOperate(User follower, User following);

    Follow select (User follower, User following);
}
