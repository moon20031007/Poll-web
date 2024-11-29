package com.poll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}

