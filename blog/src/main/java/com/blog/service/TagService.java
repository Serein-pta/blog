package com.blog.service;

import com.blog.pojo.Tag;

import java.util.List;

public interface TagService {
    //查询标签所有数据
    List<Tag> list();
    //删除标签
    void delete(Integer id);
    //新增标签
    void add(Tag tag);
    //查询标签
    Tag selectById(Integer id);
    //修改标签
    void update(Tag tag);
}
