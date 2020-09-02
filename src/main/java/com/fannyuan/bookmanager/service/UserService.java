package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.dao.UserDao;
import com.fannyuan.bookmanager.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public int addUser(User user) {
        return userDao.addUser(user);
    }

    public User getUser(int id) {
        return userDao.selectById(id);
    }

    public User getUser(String email) {
        return userDao.selectByEmail(email);
    }

    public void updateUserTime(User user) {
        userDao.updateTime(user);
    }
}
