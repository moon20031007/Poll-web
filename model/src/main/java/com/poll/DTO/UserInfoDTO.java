package com.poll.DTO;

import com.poll.pojo.Options;
import com.poll.pojo.Poll;
import com.poll.pojo.User;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserInfoDTO {
    private User user;

    private List<Poll> posts;

    private List<Map<Poll, Options>> votes;

    private List<Poll> stars;

    private List<User> followings;

    private List<User> followers;
}
