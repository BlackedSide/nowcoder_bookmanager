package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.UserOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserOrderDao {

    @Insert({
            "insert into user_order "
                    + "(user_id, book_id, order_createtime, order_expiredtime)"
                    + " values(#{userId}, #{bookId}, #{createTime}, #{expiredTime})"
    })
    int addOrder(UserOrder order);

    @Select({
            "select order_id, user_id, book_id, order_createtime, order_expiredtime"
                    + " from user_order where user_id = #{uid}"
    })
    @Results({
            @Result(property = "id", column = "order_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "createTime", column = "order_createtime"),
            @Result(property = "expiredTime", column = "order_expiredtime")
    })
    List<UserOrder> selectOrders(int uid);


    @Select({
            "select order_id, user_id, book_id, order_createtime, order_expiredtime"
                    + " from user_order where order_id = #{id}"
    })
    @Results({
            @Result(property = "id", column = "order_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "createTime", column = "order_createtime"),
            @Result(property = "expiredTime", column = "order_expiredtime")
    })
    UserOrder getOrderById(int id);

    @Delete({
            "delete from user_order "
                    + "where order_id = #{id}"
    })
    void deleteOrder(int id);
}
