package com.fannyuan.bookmanager.utils;

import com.fannyuan.bookmanager.model.User;

public class ConcurrentUtils {

    private static ThreadLocal<User> host = new ThreadLocal<>();

    public static User getHost() {
        return host.get();
    }

    public static void setHost(User user) {
        host.set(user);
    }
}
