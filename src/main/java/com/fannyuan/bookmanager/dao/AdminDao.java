package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao {
    @Select({
            "select "
            + "admin_authority, admin_createtime, admin_updatetime"
            + " from user_admin "
            + "where user_id = #{uid}"
    })
    @Results({
            @Result(property = "authority", column = "admin_authority"),
            @Result(property = "createTime", column = "admin_createtime"),
            @Result(property = "updateTime", column = "admin_updatetime")
    })
    Admin selectByUid(int uid);
}
