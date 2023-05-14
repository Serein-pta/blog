package com.blog.service;

import com.blog.pojo.Comment;
import com.blog.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {
    //评论列表分页
    PageBean page(Integer page, Integer pageSize);

    //添加评论
    void add(Comment comment);

    //删除评论
    void delete(Integer id);

    //根据博客删除而删除评论
    void deleteByBlogId(List<Integer> ids);
}
