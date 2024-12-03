package com.poll.mapper;

import com.poll.pojo.Image;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    void insert(Image image);
}
