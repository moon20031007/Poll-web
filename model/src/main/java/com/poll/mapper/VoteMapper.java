package com.poll.mapper;

import com.poll.pojo.Vote;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteMapper {

    List<Vote> selectByPollId(Integer id);

    List<Vote> selectByUserId(Integer id);

    void insert(Vote vote);

    List<Vote> recent();

    List<Vote> check(Integer userId, Integer pollId);
}
