package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Insert({
            "insert into user "
                    + "(user_name, user_email, user_password, user_createtime, user_updatetime)"
                    + " values "
                    + "(#{name}, #{email}, #{password}, #{createTime}, #{updateTime})"
    })
    int addUser(User user);

    @Select({
            "select "
                    + "user_id, user_name, user_email, user_password, user_createtime, user_updatetime"
                    + " from user "
                    + "where user_id = #{id}"
    })
    @Results({
            @Result(property = "id", column = "user_id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "email", column = "user_email"),
            @Result(property = "password", column = "user_password"),
            @Result(property = "createTime", column = "user_createtime"),
            @Result(property = "updateTime", column = "user_updatetime")
    })
    User selectById(int id);

    @Select({
            "select "
                    + "user_id, user_name, user_email, user_password, user_createtime, user_updatetime"
                    + " from user "
                    + "where user_name = #{name}"
    })
    @Results({
            @Result(property = "id", column = "user_id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "email", column = "user_email"),
            @Result(property = "password", column = "user_password"),
            @Result(property = "createTime", column = "user_createtime"),
            @Result(property = "updateTime", column = "user_updatetime")
    })
    User selectByName(String name);

    @Select({
            "select "
                    + "user_id, user_name, user_email, user_password, user_createtime, user_updatetime"
                    + " from user "
                    + "where user_email = #{email}"
    })
    @Results({
            @Result(property = "id", column = "user_id"),
            @Result(property = "name", column = "user_name"),
            @Result(property = "email", column = "user_email"),
            @Result(property = "password", column = "user_password"),
            @Result(property = "createTime", column = "user_createtime"),
            @Result(property = "updateTime", column = "user_updatetime")
    })
    User selectByEmail(String email);

    @Update({
            "update user set "
                    + "user_updatetime = #{updateTime}"
                    + " where user_id = #{id}"
    })
    void updateTime(User user);

    @Update({
            "update user set "
                    + "user_password = #{password}, user_updatetime = #{updateTime}"
                    + " where user_id = #{id}"
    })
    void updatePassword(User user);
}
