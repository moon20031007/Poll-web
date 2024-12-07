package com.poll.controller;

import com.poll.pojo.Message;
import com.poll.result.Result;
import com.poll.service.MessageService;
import com.poll.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public Result sendMessage(@RequestHeader("Authorization") String jwt, @RequestBody Message message) {
        messageService.send(JwtUtils.parseJwt(jwt), message);
        return Result.success();
    }

    @GetMapping("/get")
    public Result getMessages(@RequestHeader("Authorization") String jwt) {
        messageService.getMessages(JwtUtils.parseJwt(jwt));
        return Result.success();
    }
}
