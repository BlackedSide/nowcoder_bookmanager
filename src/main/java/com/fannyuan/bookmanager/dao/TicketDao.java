package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.Ticket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TicketDao {
    int addTicket(Ticket ticket);
    Ticket selectByUserId(int uid);
    Ticket selectByTicket(String ticket);
    void deleteTicketById(int tid);
    void deleteTicket(String ticket);
    int updateTicket(Ticket ticket);
}
