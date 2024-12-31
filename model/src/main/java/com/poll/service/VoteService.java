package com.poll.service;

import com.poll.pojo.Poll;
import com.poll.pojo.User;
import com.poll.pojo.Vote;

import java.util.List;
import java.util.Map;

public interface VoteService {

    void insert(User user, List<Vote> votes);

    Map<Vote, User> recent();

    List<Vote> check(User user, Integer pollId);
}
