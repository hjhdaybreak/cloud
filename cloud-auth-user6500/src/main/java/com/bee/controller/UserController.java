package com.bee.controller;

import com.bee.common.Result;
import com.bee.utils.JWTUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public Result login(String username, String password) {
        if ("admin".equals(username) && "12345".equals(password)) {
            String token = JWTUtils.token(username);
            return Result.builder().code(200).message("success").token(token).build();
        } else {
            return Result.builder().code(500).message("登录信息不正确").build();
        }
    }
}
