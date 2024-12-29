package com.poll.controller;

import com.poll.pojo.Poll;
import com.poll.pojo.Star;
import com.poll.result.Result;
import com.poll.result.ResultCode;
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
        try {
            starService.starOperate(JwtUtils.parseJwt(jwt), poll);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/check")
    public Result check(@RequestHeader("Authorization") String jwt, @RequestParam Poll poll) {
        try {
            Star star = starService.select(JwtUtils.parseJwt(jwt), poll);
            if (star != null) {
                return Result.success(true);
            } else {
                return Result.success(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }
}
