package com.xiao.gs.controller.api;

import com.xiao.gs.bind.annotation.CurrentUser;
import com.xiao.gs.data.domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author luoxiaoxiao
 * @date 2018-10-02 19:05
 */
@RestController
@SessionAttributes(types = User.class)
@RequestMapping("/api/user")
public class ApiUserController {

    @ApiOperation(value = "获取当前登录用户")
    @GetMapping(value = "/current-user")
    public ResponseEntity<User> currentUser(@ApiIgnore @CurrentUser final User user) {
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation(value = "根据id获取用户")
    @GetMapping(value = "/id")
    public ResponseEntity<User> getUserById(@ApiParam(value = "用户id") @RequestParam(value = "id") User user) {
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation(value = "更新session状态")
    @PutMapping(value = "/session-status/complete")
    public Boolean sessionStatus(@ApiIgnore SessionStatus status) {
        // 清空SessionAttributes中的内容
        if (!status.isComplete()) {
            status.setComplete();
            return true;
        }
        return false;
    }

}
