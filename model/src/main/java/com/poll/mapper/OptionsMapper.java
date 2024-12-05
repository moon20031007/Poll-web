package com.poll.mapper;

import com.poll.pojo.Options;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptionsMapper {

    List<Options> selectByPollId(Integer id);

    Options selectById(Integer id);
}
