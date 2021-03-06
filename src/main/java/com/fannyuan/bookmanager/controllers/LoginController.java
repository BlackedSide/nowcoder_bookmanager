package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.biz.LoginBiz;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.service.UserService;
import com.fannyuan.bookmanager.utils.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Resource
    private LoginBiz loginBiz;

    @Resource
    private UserService userService;

    @RequestMapping(path = {"/users/register"}, method = {RequestMethod.GET})
    public String register() {
        return "login/register";
    }

    @RequestMapping(path = {"/users/register/do"}, method = {RequestMethod.POST})
    public String doRegister(
            Model model,
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        try {
            String ticket = loginBiz.register(user);
            CookieUtils.writeCookie("ticket", ticket, response);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "404";
        }
    }

    @RequestMapping(path = {"/users/login"}, method = {RequestMethod.GET})
    public String login() {
        return "login/login";
    }

    @RequestMapping(path = {"/users/login/do"}, method = {RequestMethod.POST})
    public String doLogin(
            Model model,
            HttpServletResponse response,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        try {
            String ticket = loginBiz.login(email, password);
            CookieUtils.writeCookie("ticket", ticket, response);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "404";
        }
    }

    @RequestMapping(path = {"/users/logout"}, method = {RequestMethod.GET})
    public String logout(@CookieValue("ticket") String ticket) {
        loginBiz.logout(ticket);
        return "redirect:/index";
    }
}
