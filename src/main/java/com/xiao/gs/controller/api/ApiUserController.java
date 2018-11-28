package com.xiao.gs.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.gs.bind.annotation.CurrentUser;
import com.xiao.gs.data.jpa.domain.User;
import com.xiao.gs.model.JsonResult;
import com.xiao.gs.model.LoginUser;
import com.xiao.gs.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * @author luoxiaoxiao
 * @date 2018-10-02 19:05
 */
@Slf4j
@RestController
@SessionAttributes(types = User.class)
@RequestMapping(value = "/api/user")
public class ApiUserController {

    private final UserService userService;

    public ApiUserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping(value = {"", "/"})
    public JsonResult exportUser(@PageableDefault(sort = {"id"}, direction = ASC) Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Page<User> userPage = userService.findAll(pageable);
        return JsonResult.success(new PageInfo<>(new ArrayList<>(userPage.getContent())));
    }

    @ApiOperation(value = "根据id获取用户(从数据库获取,带缓存)")
    @GetMapping(value = "/cache/id")
    public ResponseEntity<User> getById(@ApiParam(value = "用户id") @RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @ApiOperation(value = "获取当前登录用户")
    @GetMapping(value = "/current-user")
    public ResponseEntity<User> currentUser(@ApiIgnore @CurrentUser final User user) {
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation(value = "根据id获取用户(通过注解注入)")
    @GetMapping(value = "/id")
    public ResponseEntity<User> getUserById(@ApiParam(value = "用户id") @RequestParam(value = "id") User user) {
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "获取session中的user")
    @GetMapping(value = "/session/get")
    public ResponseEntity<LoginUser> session(@ApiIgnore @SessionAttribute final LoginUser user) {
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "更新session状态")
    @PutMapping(value = "/session-status/complete")
    public Boolean sessionStatus(@ApiIgnore SessionStatus status) {
        // 清空SessionAttributes中的内容
        status.setComplete();
        return true;
    }

}
