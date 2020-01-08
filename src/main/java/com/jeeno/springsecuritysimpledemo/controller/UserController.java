package com.jeeno.springsecuritysimpledemo.controller;

import com.jeeno.springsecuritysimpledemo.pojo.UserDO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杜家浩
 * @version 1.0.0
 * @date 2020/1/8 17:17
 */
@RestController
@RequestMapping("/user")
// 设置该controller只有登录后才能调用
@PreAuthorize("isAuthenticated()")
public class UserController {

    @GetMapping("/admin")
    // 设置该api只有ADMIM才能调用
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String admin() {
        return "超级管理员专属api";
    }

    @GetMapping("/user")
    // 设置该api只有USER才能调用
    @PreAuthorize(value = "hasRole('USER')")
    public String user() {
        return "普通用户专属api";
    }

    @GetMapping("")
    // 设置该api可由USER或ADMIN用户调用，支持自定义拼装
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('USER')")
    public String adminOrUser() {
        return "超级管理员/普通用户api";
    }

    // 获取当前登录用户的信息
    @GetMapping("/me")
    public UserDO me(@AuthenticationPrincipal UserDO userDO) {
        return userDO;
    }
}
