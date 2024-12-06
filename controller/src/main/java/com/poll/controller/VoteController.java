package com.poll.controller;

import com.poll.pojo.Vote;
import com.poll.result.Result;
import com.poll.result.ResultCode;
import com.poll.service.VoteService;
import com.poll.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
