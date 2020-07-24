package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.utils.ConcurrentUtils;
import org.springframework.stereotype.Service;

@Service
public class HostHolder {
    public User getUser() {
        return ConcurrentUtils.getHost();
    }

    public void setUser(User user) {
        ConcurrentUtils.setHost(user);
    }
}
