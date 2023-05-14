package com.blog.service;

import com.blog.pojo.PageBean;
import com.blog.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    //添加用户
    void add(User user);
    //查询用户信息
    User selectByUserId(Integer id);
    //修改用户信息
    void update(User user);
    //分页
    PageBean page(Integer page, Integer pageSize);

    //登录
    User login(User user);
    //注销
    public void logout(HttpSession session);
}
