package com.poll.mapper;

import com.poll.pojo.Poll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PollMapper {

    List<Poll> getPolls(Integer offset, Integer size);

    void insert(Poll poll);

    Poll selectById(Integer id);

    List<Poll> selectPost(Integer id);

    List<Poll> selectStar(Integer id);
}
