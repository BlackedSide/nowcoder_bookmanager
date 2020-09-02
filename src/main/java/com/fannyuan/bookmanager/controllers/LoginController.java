package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.biz.LoginBiz;
import com.fannyuan.bookmanager.model.Ticket;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.service.TicketService;
import com.fannyuan.bookmanager.service.UserService;
import com.fannyuan.bookmanager.utils.CookieUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class LoginController {
    @Resource
    private LoginBiz loginBiz;

    @Resource
    private TicketService ticketService;

    @PostMapping(path = "/register/do",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> doRegister(
            HttpServletResponse response,
            @RequestBody User newUser
    ) {
        User user = new User();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        try {
            String ticket = loginBiz.register(user);
            CookieUtils.writeCookie("ticket", ticket, response);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/login/do",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> doLogin(
            HttpServletResponse response,
            @RequestBody User loginUser
    ) {
        try {
            String ticket = loginBiz.login(loginUser.getEmail(), loginUser.getPassword());
            CookieUtils.writeCookie("ticket", ticket, response);
            return new ResponseEntity<>(loginUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(loginUser, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<Ticket> logout(@CookieValue("ticket") String ticket) {
        Ticket getTicket = ticketService.getTicket(ticket);
        if (getTicket != null) {
            loginBiz.logout(ticket);
            return new ResponseEntity<>(getTicket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
