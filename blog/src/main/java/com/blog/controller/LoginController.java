package com.blog.controller;

import com.blog.pojo.User;
import com.blog.pojo.Result;
import com.blog.service.UserService;
import com.blog.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user/login")
    public Result login(@RequestBody User user){
        log.info("员工登录：{}",user);
        User e = userService.login(user);
        //登录成功
        if(e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getUserId());
            claims.put("username",e.getUsername());
            claims.put("password",e.getPassword());

            String jwt = JwtUtils.generateJwt(claims);//jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }

        return Result.error("用户名或密码错误");
    }


}
