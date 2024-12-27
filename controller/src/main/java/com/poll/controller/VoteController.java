package com.poll.controller;

import com.poll.pojo.Poll;
import com.poll.pojo.User;
import com.poll.pojo.Vote;
import com.poll.result.Result;
import com.poll.result.ResultCode;
import com.poll.service.VoteService;
import com.poll.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/operate")
    public Result operate(@RequestHeader("Authorization") String jwt, @RequestBody List<Vote> votes) {
        try {
            voteService.insert(JwtUtils.parseJwt(jwt), votes);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/recent")
    public Result recent() {
        try {
            Map<Vote, User> recentVotes = voteService.recent();
            return Result.success(recentVotes);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/check")
    public Result check(@RequestHeader("Authorization") String jwt, @RequestBody Poll poll) {
        try {
            return Result.success(voteService.check(JwtUtils.parseJwt(jwt), poll));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }
}
