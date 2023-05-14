package com.blog.controller;

import com.blog.pojo.Comment;
import com.blog.pojo.PageBean;
import com.blog.pojo.Result;
import com.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/comment")
@RestController
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    //新增评论
    @PostMapping("/publish")
    public Result add(@RequestBody Comment comment){
        log.info("新增评论，comment:{}",comment);
        commentService.add(comment);
        return Result.success();
    }

    //删除评论
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) throws Exception{
        log.info("根据ID删除评论：{}",id);
        commentService.delete(id);
        return Result.success();
    }

    //评论列表分页
    @GetMapping("/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = commentService.page(page,pageSize);
        return Result.success(pageBean);
    }
}
