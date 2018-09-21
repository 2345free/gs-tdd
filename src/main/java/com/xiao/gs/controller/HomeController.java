package com.xiao.gs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页控制器
 *
 * @author luoxiaoxiao
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public @ResponseBody
    String greeting() {
        return "Hello World";
    }

}
