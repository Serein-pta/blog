package com.blog.service;

import com.blog.pojo.Blog;
import com.blog.pojo.PageBean;

import java.util.List;

public interface BlogService {
    //批量删除
    void delete(List<Integer> ids);
    //新增博客
    void save(Blog blog);
    //查询博客
    Blog getByBlogId(Integer blogId);
    //修改博客
    void update(Blog blog);
    //博客列表分页
    PageBean page(Integer page, Integer pageSize, String title,String tag,Integer authorId);
}
