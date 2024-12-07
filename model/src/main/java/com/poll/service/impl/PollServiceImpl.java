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
    private final UserMapper userMapper;
    private final PollTopicsMapper pollTopicsMapper;

    public PollServiceImpl(PollMapper pollMapper, OptionsMapper optionsMapper, ImageMapper imageMapper, VoteMapper voteMapper, TopicMapper topicMapper, UserMapper userMapper, PollTopicsMapper pollTopicsMapper) {
        this.pollMapper = pollMapper;
        this.optionsMapper = optionsMapper;
        this.imageMapper = imageMapper;
        this.voteMapper = voteMapper;
        this.topicMapper = topicMapper;
        this.userMapper = userMapper;
        this.pollTopicsMapper = pollTopicsMapper;
    }

    @Override
    public List<PollInfoDTO> getPolls(Integer page, Integer size) {
        List<PollInfoDTO> pollInfoList = new ArrayList<>();
        List<Poll> polls = pollMapper.getPolls((page - 1) * size, size);
        polls.forEach(poll -> {
            PollInfoDTO pollInfoDTO = new PollInfoDTO();
            List<Options> options = optionsMapper.selectByPollId(poll.getPollId());
            List<Topic> topics = topicMapper.selectByPollId(poll.getPollId());
            List<Image> images = imageMapper.selectByPollId(poll.getPollId());
            List<Vote> votes = voteMapper.selectByPollId(poll.getPollId());
            if (poll.getAllowAnonymous()) {
                votes.stream().filter(Vote::getIsAnonymous).forEach(vote -> vote.setVoterId(null));
            }
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
            pollInfoDTO.setVotes(votes);
            pollInfoDTO.setResults(results);
            pollInfoDTO.setAvatar(userMapper.selectById(poll.getCreatorId()).getAvatar());
            pollInfoList.add(pollInfoDTO);
        });
        return pollInfoList;
    }

    @Override
    public Integer getPageSize(Integer size) {
        Integer total = pollMapper.getPollCount();
        return (int) Math.ceil((double) total / size);
    }

    @Override
    public PollInfoDTO getPollInfo(Integer id) {
        Poll poll = pollMapper.selectById(id);
        PollInfoDTO pollInfoDTO = new PollInfoDTO();
        List<Options> options = optionsMapper.selectByPollId(poll.getPollId());
        List<Topic> topics = topicMapper.selectByPollId(poll.getPollId());
        List<Image> images = imageMapper.selectByPollId(poll.getPollId());
        List<Vote> votes = voteMapper.selectByPollId(poll.getPollId());
        if (poll.getAllowAnonymous()) {
            votes.stream().filter(Vote::getIsAnonymous).forEach(vote -> vote.setVoterId(null));
        }
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
        pollInfoDTO.setVotes(votes);
        pollInfoDTO.setResults(results);
        pollInfoDTO.setAvatar(userMapper.selectById(poll.getCreatorId()).getAvatar());
        return pollInfoDTO;
    }

    @Override
    public Integer create(User user, Poll poll, List<Options> options, List<Topic> topics, List<String> images) {
        poll.setCreatorId(user.getUserId());
        pollMapper.insert(poll);
        Integer pollId = poll.getPollId();
        options.forEach(option -> {
            option.setPollId(pollId);
            optionsMapper.insert(option);
        });
        topics.forEach(topic -> pollTopicsMapper.insert(pollId, topic.getTopicId()));
        for (int i = 0; i < images.size(); i++) {
            Image image = new Image();
            image.setPollId(pollId);
            image.setOrder(i + 1);
            image.setPath(images.get(i));
            imageMapper.insert(image);
        }
        return pollId;
    }
}
