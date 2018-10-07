package com.xiao.gs.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author luoxiaoxiao
 * @date 2018-10-02 19:05
 */
@RestController
@RequestMapping("/swagger/demo")
public class SwaggerDemoController {

    @ApiOperation(value = "operation1", notes = "operation1.notes")
    @GetMapping(value = "/operation1")
    public ResponseEntity<String> operation1(
            @ApiParam(value = "Status could be one of ...", required = true)
            @RequestParam(value = "status", defaultValue = "默认值") String status) {
        return ResponseEntity.ok("operation1");
    }

    @ApiOperation(notes = "Operation 2", value = "Operation 2 do something...")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "header1", value = "Header for bla bla...")
    )
    @PostMapping(value = "/operation2")
    public ResponseEntity<String> operation2() {
        return ResponseEntity.ok("operation2");
    }

}
