package com.poll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poll.DTO.PollInfoDTO;
import com.poll.pojo.*;
import com.poll.result.Result;
import com.poll.result.ResultCode;
import com.poll.service.ImageService;
import com.poll.service.PollService;
import com.poll.service.VoteService;
import com.poll.utils.JwtUtils;
import com.poll.utils.SaveImageUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/poll")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/main")
    public Result getPolls(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            List<PollInfoDTO> pollInfoList = pollService.getPolls(page, size);
            return Result.success(pollInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/size")
    public Result getSizePages(@RequestParam(defaultValue = "3") int size) {
        try {
            Integer count = pollService.getPageSize(size);
            return Result.success(count);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/info/{id}")
    public Result getInfo(@PathVariable Integer id) {
        try {
            PollInfoDTO pollInfo = pollService.getPollInfo(id);
            return Result.success(pollInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/add")
    public Result test(@RequestHeader("Authorization") String jwt,
                       @RequestParam(value = "poll") String pollJson,
                       @RequestParam(value = "options") String optionsJson,
                       @RequestParam(value = "topics") String topicsJson,
                       @RequestParam(value = "files", required = false) MultipartFile[] files) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Poll poll = objectMapper.readValue(pollJson, Poll.class);
            List<Options> options = objectMapper.readValue(optionsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Options.class));
            List<Topic> topics = objectMapper.readValue(topicsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Topic.class));
            List<String> imageNames = new ArrayList<>();
            if (files != null) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        String name = SaveImageUtils.saveImage(file, "image");
                        imageNames.add(name);
                    }
                }
            }
            Integer pollId = pollService.create(JwtUtils.parseJwt(jwt), poll, options, topics, imageNames);
            return Result.success(pollId);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @PutMapping("/enable")
    public Result enable(@RequestHeader("Authorization") String jwt, @RequestBody Poll poll) {
        if (!JwtUtils.parseJwt(jwt).getIsAdmin()) {
            return Result.error(ResultCode.PERMISSION_NO_ACCESS);
        }
        pollService.enableOperate(poll);
        return Result.success();
    }

    @GetMapping("/all")
    public Result all(@RequestHeader("Authorization") String jwt, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        if (!JwtUtils.parseJwt(jwt).getIsAdmin()) {
            return Result.error(ResultCode.PERMISSION_NO_ACCESS);
        }
        return Result.success(pollService.getAll(page, size));
    }

    @GetMapping("/all/size")
    public Result getAllSizePages(@RequestHeader("Authorization") String jwt, @RequestParam(defaultValue = "3") int size) {
        try {
            if (!JwtUtils.parseJwt(jwt).getIsAdmin()) {
                return Result.error(ResultCode.PERMISSION_NO_ACCESS);
            }
            Integer count = pollService.getAllPageSize(size);
            return Result.success(count);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/search")
    public Result search(@RequestParam String keyword) {
        try {
            return Result.success(pollService.search(keyword));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }
}
