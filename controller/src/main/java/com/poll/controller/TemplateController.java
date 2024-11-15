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
}
