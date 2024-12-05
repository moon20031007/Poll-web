package com.poll.DTO;

import com.poll.pojo.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PollInfoDTO {
    private Poll poll;

    private List<Options> options;

    private List<Topic> topics;

    private List<Image> images;

    private List<Vote> votes;

    private Map<Integer, Integer> results;

    private String avatar;
}
