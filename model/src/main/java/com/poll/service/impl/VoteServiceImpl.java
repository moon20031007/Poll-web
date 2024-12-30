package com.poll.service.impl;

import com.poll.mapper.UserMapper;
import com.poll.mapper.VoteMapper;
import com.poll.pojo.Poll;
import com.poll.pojo.User;
import com.poll.pojo.Vote;
import com.poll.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteMapper voteMapper;
    private final UserMapper userMapper;

    public VoteServiceImpl(VoteMapper voteMapper, UserMapper userMapper) {
        this.voteMapper = voteMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void insert(User user, List<Vote> votes) {
        votes.forEach(vote -> {
            vote.setVoterId(user.getUserId());
            voteMapper.insert(vote);
        });
    }

    @Override
    public Map<Vote, User> recent() {
        Map<Vote, User> map = new HashMap<>();
        List<Vote> votes = voteMapper.recent();
        votes.forEach(vote -> {
            User user = userMapper.selectById(vote.getVoterId());
            User userToPut = new User();
            if (!vote.getIsAnonymous()) {
                userToPut.setUserId(user.getUserId());
                userToPut.setUsername(user.getUsername());
                userToPut.setAvatar(user.getAvatar());
            } else {
                userToPut.setUsername("Anonymous Vote");
                userToPut.setAvatar("default.png");
            }
            map.put(vote, userToPut);
        });
        return map;
    }

    @Override
    public List<Vote> check(User user, Integer pollId) {
        return voteMapper.check(user.getUserId(), pollId);
    }
}
