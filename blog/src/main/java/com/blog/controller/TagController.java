package com.blog.controller;

import com.blog.pojo.Tag;
import com.blog.pojo.Result;
import com.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/tag")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    //标签列表
    @GetMapping("/list")
    public Result list(){
        log.info("查询全部标签数据");
        List<Tag> tagList = tagService.list();
        return Result.success(tagList);
    }

    //删除标签
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据ID删除标签：{}",id);
        tagService.delete(id);
        return Result.success();
    }

    //新增标签
    @PostMapping("/add")
    public Result add(@RequestBody Tag tag){
        log.info("新增标签：{}",tag);
        tagService.add(tag);
        return Result.success();
    }

    //查询标签
    @GetMapping("/select/{id}")
    public  Result selectById(@PathVariable Integer id){
        log.info("通过id查询标签信息:{}",id);
        Tag tag = tagService.selectById(id);
        return Result.success(tag);
    }

    //修改标签
    @PutMapping("/modify")
    public Result update(@RequestBody Tag tag){
        log.info("修改标签信息：{}",tag);
        tagService.update(tag);
        return Result.success();
    }
}
