package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
    Admin selectByUid(int uid);
}
