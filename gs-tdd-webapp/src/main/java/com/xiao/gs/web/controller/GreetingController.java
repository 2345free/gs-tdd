package com.xiao.gs.web.controller;

import com.xiao.gs.service.GreetingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private GreetingService greetingService;

    /**
     * spring team 建议使用构造方法注入而不是 @Autowired
     *
     * @param greetingService 打招呼服务
     */
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @ResponseBody
    @GetMapping("/greeting")
    public String greeting() {
        return greetingService.greet();
    }
}