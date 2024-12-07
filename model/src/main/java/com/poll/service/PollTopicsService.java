package com.poll.service;

import com.poll.DTO.PollInfoDTO;

import java.util.List;

public interface PollTopicsService {

    List<PollInfoDTO> getPolls(Integer id, Integer page, Integer size);

    Integer getPageSize(Integer id, Integer size);
}
