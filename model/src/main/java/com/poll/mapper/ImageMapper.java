package com.poll.mapper;

import com.poll.pojo.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

    void insert(Image image);

    List<Image> selectByPollId(Integer id);
}
