package com.xiao.gs.tdd.controller;

import com.xiao.gs.tdd.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GreetingController {

    @Autowired
    private GreetingService service;

    @GetMapping("/greeting")
    public @ResponseBody
    String greeting() {
        return service.greet();
    }

}
