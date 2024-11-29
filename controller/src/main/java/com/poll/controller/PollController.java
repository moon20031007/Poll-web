package com.poll.controller;

import com.poll.pojo.Poll;
import com.poll.result.Result;
import com.poll.result.ResultCode;
import com.poll.service.PollService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/poll")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/add")
    public Result addPoll(@RequestHeader("Authorization") String jwt, @ModelAttribute Poll poll, @RequestParam(value = "files", required = false) MultipartFile[] files) {
        try {
            if (files != null) {
                for (MultipartFile file : files) {
                    System.out.println(file.getOriginalFilename());
                }
            }
//            Poll pollWithId = pollService.insert(JwtUtils.parseJwt(jwt).getUserId(), poll);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }
}
