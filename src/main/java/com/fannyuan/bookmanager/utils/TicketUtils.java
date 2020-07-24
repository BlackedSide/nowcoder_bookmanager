package com.fannyuan.bookmanager.utils;

import com.fannyuan.bookmanager.model.Ticket;
import org.joda.time.DateTime;

public class TicketUtils {

    public static Ticket next(int uid) {
        Ticket ticket = new Ticket();
        ticket.setTicket(UuidUtils.next());
        ticket.setUserId(uid);
        DateTime expiredTime = new DateTime();
        expiredTime = expiredTime.plusMonths(3);
        ticket.setExpiredAt(expiredTime.toDate());

        return ticket;
    }
}
