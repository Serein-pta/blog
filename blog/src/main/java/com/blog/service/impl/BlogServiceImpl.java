package com.blog.service.impl;

import com.blog.mapper.BlogMapper;
import com.blog.mapper.CommentMapper;
import com.blog.pojo.Blog;
import com.blog.pojo.PageBean;
import com.blog.service.BlogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentMapper commentMapper;

    //批量删除博客
    @Override
    public void delete(List<Integer> ids) {
        blogMapper.delete(ids);
        commentMapper.deleteByBlogId(ids);
    }

    //新增博客
    @Override
    public void save(Blog blog) {
        blog.setCreateTime(LocalDateTime.now());
        blog.setUpdateTime(LocalDateTime.now());
        blogMapper.insert(blog);
    }

    //根据ID查询博客
    @Override
    public Blog getByBlogId(Integer blogId) {
        return blogMapper.getByBlogId(blogId);
    }

    //修改博客
    @Override
    public void update(Blog blog) {
        blog.setUpdateTime(LocalDateTime.now());
        blogMapper.update(blog);
    }

    //博客列表分页
    @Override
    public PageBean page(Integer page, Integer pageSize, String title,String tag,Integer authorId) {
        PageHelper.startPage(page,pageSize);

        List<Blog> blogList = blogMapper.list(title,tag,authorId);
        Page<Blog> p = (Page<Blog>) blogList;
        //封装PageBean类
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }
}
