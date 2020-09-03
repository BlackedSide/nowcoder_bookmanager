package com.fannyuan.bookmanager.interceptor;

import com.fannyuan.bookmanager.model.Ticket;
import com.fannyuan.bookmanager.service.AdminService;
import com.fannyuan.bookmanager.service.TicketService;
import com.fannyuan.bookmanager.service.UserService;
import com.fannyuan.bookmanager.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;

    @Resource
    private TicketService ticketService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtils.getCookie("ticket", request);

        if (StringUtils.isEmpty(ticket)) {
            throw new Exception("用户未登录！");
        }

        Ticket new_ticket = ticketService.getTicket(ticket);
        if (new_ticket == null) {
            throw new Exception("没有该用户！");
        }

        if (new_ticket.getExpiredAt().before(new Date())) {
            throw new Exception("登录超时！");
        }

        if (adminService.selectAdminByUid(new_ticket.getUserId()) == null || adminService.selectAdminByUid(new_ticket.getUserId()).getAuthority() < 1) {
            throw new Exception("没有权限！");
        }

        return true;
    }
}
