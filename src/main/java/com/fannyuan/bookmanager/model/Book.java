package com.fannyuan.bookmanager.model;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String name;
    private String author;
    private double price;
    private int status;
}
