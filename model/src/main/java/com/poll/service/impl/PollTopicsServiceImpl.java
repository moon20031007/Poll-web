package com.poll.service.impl;

import com.poll.DTO.PollInfoDTO;
import com.poll.mapper.*;
import com.poll.pojo.*;
import com.poll.service.PollTopicsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PollTopicsServiceImpl implements PollTopicsService {

    private final PollTopicsMapper pollTopicsMapper;
    private final OptionsMapper optionsMapper;
    private final ImageMapper imageMapper;
    private final VoteMapper voteMapper;
    private final TopicMapper topicMapper;
    private final UserMapper userMapper;

    public PollTopicsServiceImpl(PollTopicsMapper pollTopicsMapper, OptionsMapper optionsMapper, ImageMapper imageMapper, VoteMapper voteMapper, TopicMapper topicMapper, UserMapper userMapper) {
        this.pollTopicsMapper = pollTopicsMapper;
        this.optionsMapper = optionsMapper;
        this.imageMapper = imageMapper;
        this.voteMapper = voteMapper;
        this.topicMapper = topicMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicMapper.getAllTopics();
    }

    @Override
    public List<PollInfoDTO> getPolls(Integer id, Integer page, Integer size) {
        List<PollInfoDTO> pollInfoList = new ArrayList<>();
        List<Poll> polls = pollTopicsMapper.getPolls(id, (page - 1) * size, size);
        polls.forEach(poll -> {
            PollInfoDTO pollInfoDTO = new PollInfoDTO();
            List<Options> options = optionsMapper.selectByPollId(poll.getPollId());
            List<Topic> topics = topicMapper.selectByPollId(poll.getPollId());
            List<Image> images = imageMapper.selectByPollId(poll.getPollId());
            List<Vote> votes = voteMapper.selectByPollId(poll.getPollId());
            if (poll.getAllowAnonymous()) {
                votes.stream().filter(Vote::getIsAnonymous).forEach(vote -> vote.setVoterId(null));
            }
            List<Map<Vote, User>> votesMap = votes.stream().map(vote -> {
                Map<Vote, User> map = new HashMap<>();
                if (vote.getIsAnonymous()) {
                    User anonymousUser = new User();
                    anonymousUser.setUsername("anonymous");
                    anonymousUser.setAvatar("default.png");
                    map.put(vote, anonymousUser);
                } else {
                    User voteUser = userMapper.selectById(vote.getVoterId());
                    voteUser.setPassword(null);
                    map.put(vote, voteUser);
                }
                return map;
            }).toList();
            Map<Integer, Integer> results = new HashMap<>();
            for (Options option : options) {
                results.put(option.getOptionId(), 0);
            }
            for (Vote vote : votes) {
                results.put(vote.getOptionId(), results.get(vote.getOptionId()) + 1);
            }
            pollInfoDTO.setPoll(poll);
            pollInfoDTO.setOptions(options);
            pollInfoDTO.setTopics(topics);
            pollInfoDTO.setImages(images);
            pollInfoDTO.setVotes(votesMap);
            pollInfoDTO.setResults(results);
            User creator = userMapper.selectById(poll.getCreatorId());
            creator.setPassword(null);
            pollInfoDTO.setCreator(creator);
            pollInfoList.add(pollInfoDTO);
        });
        return pollInfoList;
    }

    @Override
    public Integer getPageSize(Integer id, Integer size) {
        Integer total = pollTopicsMapper.getTopicCount(id);
        return (int) Math.ceil((double) total / size);
    }
}
