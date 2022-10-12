package com.paymybuddy.paywebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfigController {


    @GetMapping(value = "/")
    public String index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "index";
    }
//
//    @RequestMapping("/login-error.html")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "login";
//    }
}
