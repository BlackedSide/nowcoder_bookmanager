package com.fannyuan.bookmanager.controllers.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewBook {
    String name;
    String author;
    double price;
}
