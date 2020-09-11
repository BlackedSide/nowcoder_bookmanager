package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    int addUser(User user);
    User selectById(int id);
    User selectByName(String name);
    User selectByEmail(String email);
    void updateTime(User user);
    void updatePassword(User user);
}
