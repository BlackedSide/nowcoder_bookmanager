package com.fannyuan.bookmanager.model;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private int id;
    private int uid;
    private int authority;
    private Date createTime;
    private Date updateTime;
}
