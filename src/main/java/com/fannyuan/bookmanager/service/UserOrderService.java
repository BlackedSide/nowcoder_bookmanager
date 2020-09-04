package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.dao.UserOrderDao;
import com.fannyuan.bookmanager.model.Book;
import com.fannyuan.bookmanager.model.UserOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserOrderService {
    @Resource
    private UserOrderDao userOrderDao;

    public int borrowOneBook(UserOrder userOrder) {
        return userOrderDao.addOrder(userOrder);
    }

    public List<UserOrder> selectOrders(int uid) {
        return userOrderDao.selectOrders(uid);
    }

    public void returnOneBook(int id) {
        userOrderDao.deleteOrder(id);
    }

    public UserOrder getOrderById(int id) {
        return userOrderDao.getOrderById(id);
    }
}
