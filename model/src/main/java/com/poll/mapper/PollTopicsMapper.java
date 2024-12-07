package com.poll.mapper;

import com.poll.pojo.Poll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PollTopicsMapper {

    List<Poll> getPolls(Integer id, Integer offset, Integer size);

    Integer getTopicCount(Integer id);

    void insert(Integer pollId, Integer topicId);
}
