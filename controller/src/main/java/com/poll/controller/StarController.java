package com.poll.controller;

import com.poll.pojo.Poll;
import com.poll.result.Result;
import com.poll.service.StarService;
import com.poll.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/star")
public class StarController {

    private final StarService starService;

    public StarController(StarService starService) {
        this.starService = starService;
    }

    @PutMapping("/operate")
    public Result operate(@RequestHeader("Authorization") String jwt, @RequestBody Poll poll) {
        starService.starOperate(JwtUtils.parseJwt(jwt), poll);
        return Result.success();
    }
}
