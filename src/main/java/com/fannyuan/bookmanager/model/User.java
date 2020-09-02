package com.fannyuan.bookmanager.model;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;

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
