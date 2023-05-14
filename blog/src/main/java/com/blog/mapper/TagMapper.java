package com.blog.mapper;

import com.blog.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {
    @Select("select * from tag")
    List<Tag> list();

    @Delete("delete from tag where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into tag (name,blog_id) value (#{name},#{blogId})")
    void insert(Tag tag);

    @Select("select * from tag where id = #{id}")
    Tag selectById(Integer id);

    @Update("update tag set name = #{name} where id = #{id}")
    void update(Tag tag);
}
