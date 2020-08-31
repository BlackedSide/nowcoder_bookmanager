package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.biz.LoginBiz;
import com.fannyuan.bookmanager.controllers.request.NewUser;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.utils.CookieUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class LoginController {
    @Resource
    private LoginBiz loginBiz;

    @PostMapping(path = "/register/do",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void doRegister(
            HttpServletResponse response,
            @RequestBody NewUser newUser
    ) {
        User user = new User();
        user.setName(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        try {
            String ticket = loginBiz.register(user);
            CookieUtils.writeCookie("ticket", ticket, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(path = "/login/do",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void doLogin(
            HttpServletResponse response,
            @RequestBody NewUser loginUser
    ) {
        try {
            String ticket = loginBiz.login(loginUser.getEmail(), loginUser.getPassword());
            CookieUtils.writeCookie("ticket", ticket, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(path = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@CookieValue("ticket") String ticket) {
        loginBiz.logout(ticket);
    }
}
