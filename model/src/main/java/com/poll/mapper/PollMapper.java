package com.poll.mapper;

import com.poll.pojo.Poll;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PollMapper {

    void insert(Poll poll);
}
