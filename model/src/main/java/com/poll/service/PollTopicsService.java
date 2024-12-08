package com.poll.service;

import com.poll.DTO.PollInfoDTO;
import com.poll.pojo.Topic;

import java.util.List;

public interface PollTopicsService {

    List<Topic> getAllTopics();

    List<PollInfoDTO> getPolls(Integer id, Integer page, Integer size);

    Integer getPageSize(Integer id, Integer size);
}
