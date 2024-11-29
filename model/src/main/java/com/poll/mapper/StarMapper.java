package com.poll.mapper;

import com.poll.pojo.Star;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StarMapper {

    Star select(Integer userId, Integer pollId);

    void insert(Integer userId, Integer pollId);

    void delete(Integer starId);
}
