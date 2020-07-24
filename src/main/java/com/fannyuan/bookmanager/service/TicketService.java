package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.dao.TicketDAO;
import com.fannyuan.bookmanager.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketDAO ticketDAO;

    public void addTicket(Ticket ticket) {
        ticketDAO.addTicket(ticket);
    }

    public Ticket getTicket(int uid) {
        return ticketDAO.selectByUserId(uid);
    }

    public Ticket getTicket(String ticket) {
        return ticketDAO.selectByTicket(ticket);
    }

    public void deleteTicket(int tid) {
        ticketDAO.deleteTicketById(tid);
    }

    public void deleteTicket(String ticket) {
        ticketDAO.deleteTicket(ticket);
    }
}
