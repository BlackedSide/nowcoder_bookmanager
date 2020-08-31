package com.fannyuan.bookmanager.controllers.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NewUser {
    String username;
    String email;
    String password;
}
