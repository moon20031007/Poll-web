package com.poll.controller;

import com.poll.DTO.PollInfoDTO;
import com.poll.pojo.Topic;
import com.poll.result.Result;
import com.poll.result.ResultCode;
import com.poll.service.PollTopicsService;
import com.poll.service.TopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final PollTopicsService pollTopicsService;
    private final TopicService topicService;

    public TopicController(PollTopicsService pollTopicsService, TopicService topicService) {
        this.pollTopicsService = pollTopicsService;
        this.topicService = topicService;
    }

    @GetMapping("/all")
    public Result getAllTopics() {
        try {
            List<Topic> topics = topicService.getAllTopics();
            return Result.success(topics);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") int id, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            List<PollInfoDTO> pollInfoList = pollTopicsService.getPolls(id, page, size);
            return Result.success(pollInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/size/{id}")
    public Result size(@PathVariable("id") int id, @RequestParam(defaultValue = "3") int size) {
        try {
            Integer count = pollTopicsService.getPageSize(id, size);
            return Result.success(count);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }
}
