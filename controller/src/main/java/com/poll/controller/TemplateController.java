package com.poll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TemplateController {

    @GetMapping("/example")
    public String asd(Model model) {
        model.addAttribute("data", "example");
        return "example";
    }

    @GetMapping("/")
    public String index() {
        return "mainpage";
    }

    @GetMapping("/commentpage/{id}")
    public String commentpage(@PathVariable int id) {
        return "commentpage";
    }

    @GetMapping("/type1")
    public String commentpage1() {
        return "commentpage1";
    }

    @GetMapping("/type2")
    public String commentpage2() {
        return "commentpage2";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/forgetpw")
    public String forgetpw() {
        return "forgetpw";
    }

    @GetMapping("/resetpw")
    public String resetpw() {
        return "resetpw";
    }

    @GetMapping("/userCenter/{username}")
    public String userCenter(@PathVariable String username) {
        return "userCenter";
    }

    @GetMapping("/post")
    public String post() {
    	return "post";
    }

    @GetMapping("/searchResult")
    public String searchResult() {
    	return "searchResult";
    }

    @GetMapping("/messages")
    public String messages() {
    	return "messages";
    }

    @GetMapping("/topic/{id}")
    public String Dailylife(@PathVariable int id) {
        return "topic";
    }
}
