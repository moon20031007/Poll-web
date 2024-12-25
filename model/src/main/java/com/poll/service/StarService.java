package com.poll.service;

import com.poll.pojo.Poll;
import com.poll.pojo.Star;
import com.poll.pojo.User;

public interface StarService {

    void starOperate(User user, Poll poll);

    Star select(User user, Poll poll);
}
