package com.blog.controller;

import com.blog.pojo.Blog;
import com.blog.pojo.PageBean;
import com.blog.pojo.Result;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.service.MarkdownService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/blog")
@RestController
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private MarkdownService markdownService;

    @Autowired
    private CommentService commentService;

    //博客分页查询
    @GetMapping("/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String title,String tag,Integer authorId){
        log.info("分页查询博客，参数：{},{},{},{},{}",page,pageSize,title,tag,authorId);

        PageBean pageBean = blogService.page(page,pageSize,title,tag,authorId);
        return Result.success(pageBean);
    }

    //批量删除博客
    @DeleteMapping("/delete/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除博客，ids:{}",ids);
        blogService.delete(ids);
        commentService.deleteByBlogId(ids);
        return Result.success();
    }

    //新增(发布)博客
    @PostMapping("publish")
    public Result save(@RequestBody Blog blog){
        log.info("新增博客，blog:{}",blog);
        blogService.save(blog);
        return Result.success();
    }

    //查询博客详情
    @GetMapping("/detail/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询博客信息，id:{}",id);
        Blog blog = blogService.getByBlogId(id);
        return Result.success(blog);
    }

    //修改(更新)博客
    @PutMapping("/modify")
    public Result update(@RequestBody Blog blog){
        log.info("更新博客信息：()",blog);
        blogService.update(blog);
        return Result.success();
    }

    //markdown渲染博客内容
    @GetMapping("/{id}")
    public Blog getBlog(@PathVariable Integer id) throws IOException {
        Blog blog = blogService.getByBlogId(id);
        blog.setContent(markdownService.render(blog.getContent()));
        return blog;
    }

}
