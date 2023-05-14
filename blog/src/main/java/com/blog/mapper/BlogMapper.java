package com.blog.mapper;

import com.blog.pojo.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

@Mapper
public interface BlogMapper {
    //批量删除博客
    void delete(List<Integer> ids);

    //新增博客
    @Insert("insert into blog(title, content, image,tag,isPrivate, author_id, create_time, update_time) " +
            "VALUES (#{title},#{content},#{image},#{tag},#{isPrivate},#{authorId},#{createTime},#{updateTime})")
    void insert(Blog blog);

    //ID查询博客
    @Select("select * from blog where blog_id = #{blogId}")
    Blog getByBlogId(Integer blogId);

    //修改博客  动态SQL
    public void update(Blog blog);

    //分页查询博客
    //xml文件映射动态SQL
    public List<Blog> list(String title,String tag, Integer authorId);

}
