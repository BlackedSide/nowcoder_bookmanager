package com.fannyuan.bookmanager.biz;

import com.fannyuan.bookmanager.model.Ticket;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.model.exceptions.LoginRegisterException;
import com.fannyuan.bookmanager.service.TicketService;
import com.fannyuan.bookmanager.service.UserService;
import com.fannyuan.bookmanager.utils.ConcurrentUtils;
import com.fannyuan.bookmanager.utils.MD5;
import com.fannyuan.bookmanager.utils.TicketUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class LoginBiz {
    @Resource(name = "userService")
    private UserService userService;

    @Resource
    private TicketService ticketService;

    public String login(String email, String password) throws Exception {
        User user = userService.getUser(email);

        if (user == null) {
            throw new LoginRegisterException("邮箱不存在！");
        }
        if (!StringUtils.equals(MD5.next(password), user.getPassword())) {
            throw new LoginRegisterException("密码错误！");
        }

        Ticket ticket = ticketService.getTicket(user.getId());

        if (ticket == null) {
            ticket = TicketUtils.next(user.getId());
            ticketService.addTicket(ticket);
            return ticket.getTicket();
        }

        if (ticket.getExpiredAt().before(new Date())) {
            ticketService.deleteTicket(ticket.getId());
        }

        ticket = TicketUtils.next(user.getId());
        ticketService.addTicket(ticket);

        ConcurrentUtils.setHost(user);
        return ticket.getTicket();
    }

    public void logout(String ticket) {
        ticketService.deleteTicket(ticket);
    }

    public String register(User user) throws Exception {
        if (userService.getUser(user.getEmail()) != null) {
            throw new LoginRegisterException("邮箱已被注册！");
        }

        String md5 = MD5.next(user.getPassword());
        user.setPassword(md5);
        userService.addUser(user);

        Ticket ticket = TicketUtils.next(user.getId());
        ticketService.addTicket(ticket);

        ConcurrentUtils.setHost(user);
        return ticket.getTicket();
    }
}
