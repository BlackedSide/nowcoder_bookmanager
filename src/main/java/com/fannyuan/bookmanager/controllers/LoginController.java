package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.biz.LoginBiz;
import com.fannyuan.bookmanager.controllers.request.NewUser;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.service.UserService;
import com.fannyuan.bookmanager.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/users")
public class LoginController {
    @Resource
    private LoginBiz loginBiz;

    @Resource
    private UserService userService;

//    @RequestMapping(path = {"/users/register"}, method = {RequestMethod.GET})
//    public String register() {
//        return "login/register";
//    }

//    @RequestMapping(path = {"/users/register/do"}, method = {RequestMethod.POST})
    @PostMapping(path = "/register/do",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpStatus doRegister(
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
            return HttpStatus.OK;
        } catch (Exception e) {
            log.info(String.valueOf(e));
            return HttpStatus.BAD_REQUEST;
        }
    }

//    @RequestMapping(path = {"/users/login"}, method = {RequestMethod.GET})
//    public String login() {
//        return "login/login";
//    }

//    @RequestMapping(path = {"/users/login/do"}, method = {RequestMethod.POST})
    @PostMapping(path = "/login/do",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpStatus doLogin(
            HttpServletResponse response,
            @RequestBody NewUser loginUser
    ) {
        try {
            String ticket = loginBiz.login(loginUser.getEmail(), loginUser.getPassword());
            CookieUtils.writeCookie("ticket", ticket, response);
            return HttpStatus.OK;
        } catch (Exception e) {
//            model.addAttribute("error", e.getMessage());

            return HttpStatus.BAD_REQUEST;
        }
    }

//    @RequestMapping(path = {"/users/logout"}, method = {RequestMethod.GET})
    @GetMapping(path = "/logout")
    public HttpStatus logout(@CookieValue("ticket") String ticket) {
        loginBiz.logout(ticket);
        return HttpStatus.OK;
    }
}
