package com.poll.controller;

import com.poll.pojo.User;
import com.poll.service.FollowService;
import com.poll.utils.JwtUtils;
import com.poll.result.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PutMapping("/operate")
    public Result follow(@RequestHeader("Authorization") String jwt, @RequestBody User user) {
        try {
            followService.followOperate(JwtUtils.parseJwt(jwt), user);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }
}
