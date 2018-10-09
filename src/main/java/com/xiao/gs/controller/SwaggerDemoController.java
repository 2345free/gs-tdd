package com.xiao.gs.controller;

import com.xiao.gs.bind.annotation.CurrentUser;
import com.xiao.gs.data.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author luoxiaoxiao
 * @date 2018-10-02 19:05
 */
@RestController
@RequestMapping("/swagger/demo")
public class SwaggerDemoController {

    @ApiOperation(value = "operation1", notes = "operation1.notes")
    @GetMapping(value = "/operation1")
    public ResponseEntity<Object> operation1(
            @ApiParam(value = "Status could be one of ...", required = true)
            @RequestParam(value = "status", defaultValue = "默认值") String status) {
        return new ResponseEntity<>("operation1", HttpStatus.OK);
    }

    @ApiOperation(notes = "Operation 2", value = "Operation 2 do something...")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "header1", value = "Header for bla bla...")
    )
    @GetMapping(value = "/operation2")
    public ResponseEntity<User> operation2(@ApiIgnore @CurrentUser User user) {
        return ResponseEntity.ok().body(user);
    }

}
