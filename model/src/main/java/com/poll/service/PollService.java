package com.poll.service;

import com.poll.DTO.PollInfoDTO;
import com.poll.pojo.Poll;

import java.util.List;

public interface PollService {

    List<PollInfoDTO> getPolls(Integer page, Integer size);

    Integer getPageSize(Integer size);

    PollInfoDTO getPollInfo(Integer id);

    Poll insert(Integer id, Poll poll);
}
