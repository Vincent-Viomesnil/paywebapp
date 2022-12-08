package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String viewUserHome(@AuthenticationPrincipal UserPrincipal user, Model model) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("userconnected", userConnected);
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }


//
//    @RequestMapping("/login-error.html")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "login";
//    }
}
