package com.fannyuan.bookmanager.model;

import lombok.Data;

import java.util.Date;

@Data
public class Ticket {
    private int id;
    private int userId;
    private String ticket;
    private Date expiredAt;
}
