package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.dao.TicketDao;
import com.fannyuan.bookmanager.model.Ticket;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TicketService {
    @Resource
    private TicketDao ticketDao;

    public void addTicket(Ticket ticket) {
        ticketDao.addTicket(ticket);
    }

    public Ticket getTicket(int uid) {
        return ticketDao.selectByUserId(uid);
    }

    public Ticket getTicket(String ticket) {
        return ticketDao.selectByTicket(ticket);
    }

    public void deleteTicket(int tid) {
        ticketDao.deleteTicketById(tid);
    }

    public void deleteTicket(String ticket) {
        ticketDao.deleteTicket(ticket);
    }

    public int updateTicket(Ticket ticket) {
        return ticketDao.updateTicket(ticket);
    }
}
