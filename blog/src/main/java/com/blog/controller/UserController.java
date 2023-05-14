package com.blog.controller;

import com.blog.pojo.PageBean;
import com.blog.pojo.User;
import com.blog.pojo.Result;
import com.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result add(@RequestBody User user){
        log.info("新增用户：{}",user);
        userService.add(user);
        return Result.success();
    }

    @GetMapping("/select/{id}")
    public  Result selectByUserId(@PathVariable Integer id){
        log.info("通过id获取用户信息:{}",id);
        User user = userService.selectByUserId(id);
        return Result.success(user);
    }

    @PutMapping("/modify")
    public Result update(@RequestBody User user){
        log.info("修改用户信息：{}",user);
        userService.update(user);
        return Result.success();
    }

    //用户分页
    @GetMapping("/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = userService.page(page, pageSize);
        return Result.success(pageBean);
    }
}
