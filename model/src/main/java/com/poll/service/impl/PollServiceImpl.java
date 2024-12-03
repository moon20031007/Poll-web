package com.poll.service.impl;

import com.poll.mapper.OptionsMapper;
import com.poll.mapper.PollMapper;
import com.poll.pojo.Options;
import com.poll.pojo.Poll;
import com.poll.service.PollService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PollServiceImpl implements PollService {

    private final PollMapper pollMapper;

    private final OptionsMapper optionsMapper;

    public PollServiceImpl(PollMapper pollMapper, OptionsMapper optionsMapper) {
        this.pollMapper = pollMapper;
        this.optionsMapper = optionsMapper;
    }

    @Override
    public Map<Poll, List<Options>> getPolls(int page, int size) {
        Map<Poll, List<Options>> map = new HashMap<>();
        List<Poll> polls = pollMapper.getPolls((page - 1) * size, size);
        for (Poll poll : polls) {
            List<Options> options = optionsMapper.selectByPollId(poll.getPollId());
            map.put(poll, options);
        }
        return map;
    }

    @Override
    public Poll insert(Integer id, Poll poll) {
        poll.setCreatorId(id);
        pollMapper.insert(poll);
        return poll;
    }
}
