package com.blog.mapper;

import com.blog.pojo.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    //新增评论
    @Insert("insert into comment (content,blog_id,commenter_id,create_time) values (#{content},#{blogId},#{commenterId},#{createTime})")
    void insert(Comment comment);

    //删除评论
    @Delete("delete from comment where comment_id = #{commentId}")
    void delete(Integer commentId);

    //评论分页
    @Select("select count(*) from comment")
    public Long count();
    @Select("select * from comment limit #{start},#{pageSize}")
    public List<Comment> page(Integer start,Integer pageSize);

    /*根据博客ID删除该博客下的评论*/
    void deleteByBlogId(List<Integer> ids);
}
