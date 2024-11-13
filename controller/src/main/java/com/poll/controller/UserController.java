package com.poll.controller;

import com.poll.pojo.User;
import com.poll.service.UserService;
import com.poll.utils.JwtUtils;
import com.poll.result.*;
import com.poll.utils.SaveImageUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User userToLogin = userService.login(user.getEmail(), user.getPassword());
        if (userToLogin != null) {
            String jwt = JwtUtils.generateJwt(userToLogin);
            return Result.success(jwt);
        }
        return Result.error(ResultCode.ERROR);
    }

//    服务端设置响应头
//    @PostMapping("/login")
//    public Result login(@RequestBody User user, HttpServletResponse response) {
//        User userToLogin = userService.login(user.getEmail(), user.getPassword());
//        if (userToLogin != null) {
//            String jwt = JwtUtils.generateJwt(userToLogin);
//            // 设置响应头
//            response.setHeader("Authorization", "Bearer " + jwt);
//            return Result.success(jwt);
//        }
//        return Result.error(ResultCode.ERROR);
//    }

    @PostMapping("/register/step/1")
    public Result registerStepOne(@RequestBody User user) {
        try {
            userService.registerStepOne(user);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/register/step/2")
    public Result registerStepOTwo(@RequestBody User user, @RequestParam String verification) {
        if(userService.registerStepTwo(user, verification)){
            return Result.success();
        }
        return Result.error(ResultCode.ERROR);
    }

    @PostMapping("/avatar/update")
    public Result avatar(@RequestHeader("Authorization") String jwt, @RequestParam("file") MultipartFile file) throws IOException {
        String newName = SaveImageUtils.saveImage(file, "avatar");
        String oldName = userService.avatar(JwtUtils.parseJwt(jwt), newName);
        if (!Objects.equals(oldName, "default.png")) {
            SaveImageUtils.deleteImage(oldName, "avatar");
        }
        return Result.success();
    }

    @PostMapping("/profile/update")
    public Result updateProfile(@RequestHeader("Authorization") String jwt, @RequestBody User user) {
        userService.updateProfile(JwtUtils.parseJwt(jwt).getUserId(), user.getProfile());
        return Result.success();
    }
}
