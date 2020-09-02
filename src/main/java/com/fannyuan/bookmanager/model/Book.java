package com.fannyuan.bookmanager.model;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private int id;
    private String name;
    private String author;
    private double price;
    private int status; // 状态，在BookStatus中声明
    private int remains; // 剩余本数
    private int stars; // 获得点赞数
    private Date createTime;
    private Date updateTime;
}
