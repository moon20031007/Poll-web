package com.poll.service.impl;

import com.poll.DTO.PollInfoDTO;
import com.poll.mapper.*;
import com.poll.pojo.*;
import com.poll.service.PollService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PollServiceImpl implements PollService {

    private final PollMapper pollMapper;

    private final OptionsMapper optionsMapper;

    private final ImageMapper imageMapper;

    private final VoteMapper voteMapper;

    private final TopicMapper topicMapper;

    public PollServiceImpl(PollMapper pollMapper, OptionsMapper optionsMapper, ImageMapper imageMapper, VoteMapper voteMapper, TopicMapper topicMapper) {
        this.pollMapper = pollMapper;
        this.optionsMapper = optionsMapper;
        this.imageMapper = imageMapper;
        this.voteMapper = voteMapper;
        this.topicMapper = topicMapper;
    }

    @Override
    public List<PollInfoDTO> getPolls(int page, int size) {
        List<PollInfoDTO> pollInfoList = new ArrayList<>();
        List<Poll> polls = pollMapper.getPolls((page - 1) * size, size);
        for (Poll poll : polls) {
            PollInfoDTO pollInfoDTO = new PollInfoDTO();
            List<Options> options = optionsMapper.selectByPollId(poll.getPollId());
            List<Topic> topics = topicMapper.selectByPollId(poll.getPollId());
            List<Image> images = imageMapper.selectByPollId(poll.getPollId());
            List<Vote> votes = voteMapper.selectByPollId(poll.getPollId());
            pollInfoDTO.setPoll(poll);
            pollInfoDTO.setOptions(options);
            pollInfoDTO.setTopics(topics);
            pollInfoDTO.setImages(images);
            pollInfoDTO.setVotes(votes);
            pollInfoList.add(pollInfoDTO);
        }
        return pollInfoList;
    }

    @Override
    public Poll insert(Integer id, Poll poll) {
        poll.setCreatorId(id);
        pollMapper.insert(poll);
        return poll;
    }
}
