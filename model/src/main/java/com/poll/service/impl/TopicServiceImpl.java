package com.poll.service.impl;

import com.poll.mapper.TopicMapper;
import com.poll.pojo.Topic;
import com.poll.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicMapper topicMapper;

    public TopicServiceImpl(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicMapper.getAllTopics();
    }
}
