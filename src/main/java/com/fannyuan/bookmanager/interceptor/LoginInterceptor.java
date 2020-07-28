package com.fannyuan.bookmanager.interceptor;

import com.fannyuan.bookmanager.model.Ticket;
import com.fannyuan.bookmanager.service.TicketService;
import com.fannyuan.bookmanager.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private TicketService ticketService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtils.getCookie("ticket", request);

        if (StringUtils.isEmpty(ticket)) {
            response.sendRedirect("/users/login");
            return false;
        }

        Ticket new_ticket = ticketService.getTicket(ticket);
        if (new_ticket == null) {
            response.sendRedirect("/users/login");
            return false;
        }

        if (new_ticket.getExpiredAt().before(new Date())) {
            response.sendRedirect("/users/login");
            return false;
        }

        return true;
    }
}
