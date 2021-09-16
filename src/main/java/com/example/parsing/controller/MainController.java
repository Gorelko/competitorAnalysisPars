package com.example.parsing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage1(
            Map<String, Object> model
    ) {

        return "main";
    }

    @GetMapping("/main")
    public String mainPage2(
            Map<String, Object> model
    ) {

        return "main";
    }


}
