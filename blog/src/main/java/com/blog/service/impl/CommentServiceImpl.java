package com.blog.service.impl;

import com.blog.mapper.CommentMapper;
import com.blog.pojo.Comment;
import com.blog.pojo.PageBean;
import com.blog.service.CommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    //评论分页
    @Override
    public PageBean page(Integer page, Integer pageSize) {
        Long count = commentMapper.count();
        Integer start = (page - 1)*pageSize;
        List<Comment> commentList = commentMapper.page(start,pageSize);
        //封装pageBean类
        PageBean pageBean = new PageBean(count,commentList);

        return pageBean;
    }

    //新增评论
    @Override
    public void add(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        commentMapper.insert(comment);
    }

    //删除评论
    @Override
    public void delete(Integer id) {
        commentMapper.delete(id);
    }

    //根据删除博客删除评论
    @Override
    public void deleteByBlogId(List<Integer> ids) {
        commentMapper.deleteByBlogId(ids);
    }


}
