package com.poll.service;

import com.poll.DTO.PollInfoDTO;
import com.poll.pojo.Options;
import com.poll.pojo.Poll;
import com.poll.pojo.Topic;
import com.poll.pojo.User;

import java.util.List;

public interface PollService {

    List<PollInfoDTO> getPolls(Integer page, Integer size);

    Integer getPageSize(Integer size);

    PollInfoDTO getPollInfo(Integer id);

    Integer create(User user, Poll poll, List<Options> options, List<Topic> topics, List<String> Images);

    void enableOperate(Poll poll);

    List<PollInfoDTO> getAll(Integer page, Integer size);

    Integer getAllPageSize(Integer size);

    List<PollInfoDTO> search(String keyword);
}
