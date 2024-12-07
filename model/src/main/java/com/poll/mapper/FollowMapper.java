package com.poll.mapper;

import com.poll.pojo.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {

    Follow select(Integer followerId, Integer followingId);

    void insert(Integer followerId, Integer followingId);

    void delete(Integer followId);

    List<Follow> selectByUserId(Integer id);
}
