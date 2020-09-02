package com.fannyuan.bookmanager.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserOrder {
    private int id;
    private int userId;
    private int bookId;
    private Date createTime;
    private Date expiredTime;
}
