package com.poll.service.impl;

import com.poll.mapper.PollMapper;
import com.poll.pojo.Poll;
import com.poll.service.PollService;
import org.springframework.stereotype.Service;

@Service
public class PollServiceImpl implements PollService {

    private final PollMapper pollMapper;

    public PollServiceImpl(PollMapper pollMapper) {
        this.pollMapper = pollMapper;
    }

    @Override
    public Poll insert(Integer id, Poll poll) {
        poll.setCreatorId(id);
        pollMapper.insert(poll);
        return poll;
    }
}
