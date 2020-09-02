package com.fannyuan.bookmanager.interceptor;

import com.fannyuan.bookmanager.model.Ticket;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.service.TicketService;
import com.fannyuan.bookmanager.service.UserService;
import com.fannyuan.bookmanager.utils.ConcurrentUtils;
import com.fannyuan.bookmanager.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;

    @Resource
    private TicketService ticketService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtils.getCookie("ticket", request);
        if (!StringUtils.isEmpty(ticket)) {
            Ticket new_ticket = ticketService.getTicket(ticket);
            if (new_ticket != null && new_ticket.getExpiredAt().after(new Date())) {
                User host = userService.getUser(new_ticket.getUserId());
                ConcurrentUtils.setHost(host);
            }
        }
        return true;
    }
}