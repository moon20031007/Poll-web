package com.poll.service;

import com.poll.pojo.Options;
import com.poll.pojo.Poll;

import java.util.List;
import java.util.Map;

public interface PollService {

    Map<Poll, List<Options>> getPolls(int page, int size);

    Poll insert(Integer id, Poll poll);
}
