package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.dao.UserDAO;
import com.fannyuan.bookmanager.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDAO userDAO;

    public int addUser(User user) {
        return userDAO.addUser(user);
    }

    public User getUser(int id) {
        return userDAO.selectById(id);
    }

    public User getUser(String email) {
        return userDAO.selectByEmail(email);
    }
}
