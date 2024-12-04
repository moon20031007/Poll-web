package com.poll.service;

import com.poll.DTO.PollInfoDTO;
import com.poll.pojo.Poll;

import java.util.List;

public interface PollService {

    List<PollInfoDTO> getPolls(int page, int size);

    Poll insert(Integer id, Poll poll);
}
