package com.poll.DTO;

import com.poll.pojo.Image;
import com.poll.pojo.Options;
import com.poll.pojo.Poll;
import com.poll.pojo.Vote;
import lombok.Data;

import java.util.List;

@Data
public class PollInfoDTO {
    private Poll poll;

    private List<Options> options;

    private List<Image> images;

    private List<Vote> votes;
}
