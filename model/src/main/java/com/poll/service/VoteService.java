package com.poll.service;

import com.poll.pojo.User;
import com.poll.pojo.Vote;

import java.util.List;

public interface VoteService {

    void insert(User user, List<Vote> votes);
}
