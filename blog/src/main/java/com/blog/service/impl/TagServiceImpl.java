package com.blog.service.impl;

import com.blog.mapper.TagMapper;
import com.blog.pojo.Tag;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> list() {
        return tagMapper.list();
    }

    @Override
    public void delete(Integer id) {
        tagMapper.deleteById(id);
    }

    @Override
    public void add(Tag tag) {
        tagMapper.insert(tag);
    }

    @Override
    public Tag selectById(Integer id) {
        Tag tag = tagMapper.selectById(id);
        return tag;
    }

    @Override
    public void update(Tag tag) {
        tagMapper.update(tag);
    }
}
