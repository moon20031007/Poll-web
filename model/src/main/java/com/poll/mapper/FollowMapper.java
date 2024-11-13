package com.poll.mapper;

import com.poll.pojo.Follow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {

    Follow select(Integer followerId, Integer followingId);

    void insert(Integer followerId, Integer followingId);

    void delete(Integer followId);
}
