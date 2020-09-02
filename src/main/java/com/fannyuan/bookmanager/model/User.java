package com.fannyuan.bookmanager.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Date createTime;
    private Date updateTime;
}
