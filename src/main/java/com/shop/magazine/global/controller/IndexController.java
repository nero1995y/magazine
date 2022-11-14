package com.shop.magazine.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "user";
    };

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    };


    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "manager";
    };

    @GetMapping("/login")
    public String login() {
        return "login-form";
    };


    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "join-form";
    }

}
