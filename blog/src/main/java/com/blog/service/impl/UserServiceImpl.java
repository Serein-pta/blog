package com.blog.service.impl;

import com.blog.mapper.BlogMapper;
import com.blog.mapper.UserMapper;
import com.blog.pojo.Comment;
import com.blog.pojo.PageBean;
import com.blog.pojo.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override//注册用户
    public void add(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userMapper.inset(user);
    }

    @Override//登录
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }

    @Override//注销
    public void logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }

    @Override//id查询用户
    public User selectByUserId(Integer userId) {
        User user = userMapper.selectByUserId(userId);
        return user;
    }

    @Override//修改用户信息
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    //用户分页
    @Override
    public PageBean page(Integer page, Integer pageSize) {
        Long count = userMapper.count();
        Integer start = (page - 1)*pageSize;
        List<User> userList = userMapper.page(start,pageSize);
        //封装pageBean类
        PageBean pageBean = new PageBean(count,userList);

        return pageBean;
    }


}
