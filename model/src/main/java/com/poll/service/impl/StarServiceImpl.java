package com.poll.service.impl;

import com.poll.mapper.StarMapper;
import com.poll.pojo.Poll;
import com.poll.pojo.Star;
import com.poll.pojo.User;
import com.poll.service.PollService;
import com.poll.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService {

    private final StarMapper starMapper;

    public StarServiceImpl(StarMapper starMapper) {
        this.starMapper = starMapper;
    }

    @Override
    public void starOperate(User user, Poll poll) {
        Star isStarred = starMapper.select(user.getUserId(), poll.getPollId());
        if (isStarred == null) {
            starMapper.insert(user.getUserId(), poll.getPollId());
        } else {
            starMapper.delete(isStarred.getStarId());
        }
    }

    @Override
    public Star select(User user, Poll poll) {
        return starMapper.select(user.getUserId(), poll.getPollId());
    }
}
