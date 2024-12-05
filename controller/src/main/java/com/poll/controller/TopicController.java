package com.poll.controller;

import com.poll.result.Result;
import com.poll.service.PollTopicsService;
import com.poll.service.TopicService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final PollTopicsService pollTopicsService;

    public TopicController(PollTopicsService pollTopicsService) {
        this.pollTopicsService = pollTopicsService;
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") int id, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        return Result.success();
    }
}
