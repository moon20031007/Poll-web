package com.poll.service.impl;

import com.poll.mapper.VoteMapper;
import com.poll.pojo.User;
import com.poll.pojo.Vote;
import com.poll.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteMapper voteMapper;

    public VoteServiceImpl(VoteMapper voteMapper) {
        this.voteMapper = voteMapper;
    }

    @Override
    public void insert(User user, List<Vote> votes) {
        votes.forEach(vote -> {
            vote.setVoterId(user.getUserId());
            voteMapper.insert(vote);
        });
    }
}
