package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.dao.UserDAO;
import com.fannyuan.bookmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
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
