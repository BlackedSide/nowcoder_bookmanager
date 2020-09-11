package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.UserOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserOrderDao {
    int addOrder(UserOrder order);
    List<UserOrder> selectOrders(int uid);
    UserOrder getOrderById(int id);
    void deleteOrder(int id);
}
