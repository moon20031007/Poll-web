package com.poll.mapper;

import com.poll.pojo.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMapper {

    List<Topic> getAllTopics();

    List<Topic> selectByPollId(Integer id);
}
