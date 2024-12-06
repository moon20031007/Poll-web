package com.poll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poll.DTO.PollInfoDTO;
import com.poll.pojo.Image;
import com.poll.pojo.Options;
import com.poll.pojo.Poll;
import com.poll.result.Result;
import com.poll.result.ResultCode;
import com.poll.service.ImageService;
import com.poll.service.PollService;
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
    private final ImageService imageService;

    public PollController(PollService pollService, ImageService imageService) {
        this.pollService = pollService;
        this.imageService = imageService;
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
        Integer count = pollService.getPageSize(size);
        return Result.success(count);
    }

    @PostMapping("/add")
    public Result addPoll(@RequestHeader("Authorization") String jwt, @ModelAttribute Poll poll, @RequestParam(value = "files", required = false) MultipartFile[] files) {
        List<String> imageNames = new ArrayList<>();
        try {
            if (files != null) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        String name = SaveImageUtils.saveImage(file, "image");
                        imageNames.add(name);
                    }
                }
            }
            Poll pollWithId = pollService.insert(JwtUtils.parseJwt(jwt).getUserId(), poll);
            for (int i = 0; i < imageNames.size(); i++) {
                Image image = new Image();
                image.setPollId(pollWithId.getPollId());
                image.setOrder(i + 1);
                image.setPath(imageNames.get(i));
                imageService.insert(image);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/test")
    public Result test(@RequestParam(value = "poll") String pollJson,
                       @RequestParam(value = "options") String optionsJson,
                       @RequestParam(value = "files", required = false) MultipartFile[] files) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 解析JSON字符串
            Poll poll = objectMapper.readValue(pollJson, Poll.class);
            List<Options> options = objectMapper.readValue(optionsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Options.class));

            // 打印接收到的Poll对象
            System.out.println("Received Poll: " + poll);

            // 打印接收到的Options列表
            System.out.println("Received Options: " + options);

            List<String> imageNames = new ArrayList<>();
            if (files != null) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        imageNames.add(file.getOriginalFilename());
                    }
                }
            }

            // 打印文件名
            for (String imageName : imageNames) {
                System.out.println("File Name: " + imageName);
            }

            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }
}
