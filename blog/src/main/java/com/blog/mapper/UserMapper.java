package com.blog.mapper;

import com.blog.pojo.Comment;
import com.blog.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

/*    //用户注销
    @Delete("delete from user where user_id=#{userId} ")
    void deleteByUserId(Integer userId);*/

    //注册用户
    @Insert("insert into user(username, password, email) " +
            "VALUES (#{username},#{password},#{email})")
    void inset(User user);

    //查询用户
    @Select("select * from user where user_id = #{userId}")
    User selectByUserId(Integer userId);

    //用户分页
    @Select("select count(*) from user")
    public Long count();
    @Select("select * from user limit #{start},#{pageSize}")
    public List<User> page(Integer start, Integer pageSize);

    //修改用户,xml映射动态SQL
    public void update(User user);

    /*根据用户名和密码查询员工*/
    @Select("select * from user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);
}
