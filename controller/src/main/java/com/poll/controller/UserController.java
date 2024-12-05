package com.poll.controller;

import com.poll.DTO.UserInfoDTO;
import com.poll.pojo.User;
import com.poll.service.UserService;
import com.poll.utils.HashUtils;
import com.poll.utils.JwtUtils;
import com.poll.result.*;
import com.poll.utils.SaveImageUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info/{username}")
    public Result getUser(@PathVariable String username) {
        UserInfoDTO userInfo = userService.selectUserInfo(username);
        if (userInfo == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        return Result.success(userInfo);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User userToLogin = userService.login(user.getEmail());
        if (userToLogin == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        } else if (!userToLogin.getEnabled()) {
            return Result.error(ResultCode.USER_ACCOUNT_FORBIDDEN);
        }
        if (HashUtils.checkPassword(user.getPassword(), userToLogin.getPassword())) {
            String jwt = JwtUtils.generateJwt(userToLogin);
            return Result.success(jwt);
        } else {
            return Result.error(ResultCode.PARAM_IS_INVALID);
        }
    }

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
        return Result.error(ResultCode.USER_REGISTER_ERROR);
    }

    @PostMapping("/password/reset")
    public Result resetPassword(@RequestBody User user, @RequestParam String verification) {
        String result = userService.passwordReset(user, verification);
        if (Objects.equals(result, "verification error")) {
            return Result.error(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success();
    }

    @GetMapping("/password/step/1")
    public Result passwordResetStepOne(@RequestHeader("Authorization") String jwt) {
        try {
            userService.passwordResetStepOne(JwtUtils.parseJwt(jwt));
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/password/step/2")
    public Result passwordResetStepTwo(@RequestHeader("Authorization") String jwt, @RequestBody User user, @RequestParam String verification) {
        if(userService.passwordResetStepTwo(JwtUtils.parseJwt(jwt), user.getPassword(), verification)){
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

    @PostMapping("/info/update")
    public Result updateInfo(@RequestHeader("Authorization") String jwt, @RequestBody User user) {
        userService.updateInfo(JwtUtils.parseJwt(jwt).getUserId(), user);
        return Result.success();
    }

    @PutMapping("/enable")
    public Result enable(@RequestHeader("Authorization") String jwt, @RequestBody User user) {
        if (!JwtUtils.parseJwt(jwt).getIsAdmin()) {
            return Result.error(ResultCode.PERMISSION_NO_ACCESS);
        }
        userService.enableOperate(user);
        return Result.success();
    }
}
