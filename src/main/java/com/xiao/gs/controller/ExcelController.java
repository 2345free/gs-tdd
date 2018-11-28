package com.xiao.gs.controller;

import com.xiao.gs.data.jpa.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author luoxiaoxiao
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    private final UserRepository userRepository;

    public ExcelController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String exportUserExcel(ModelMap model) {
        model.put("users", userRepository.findAll());
        return "userExcelView";
    }

}
