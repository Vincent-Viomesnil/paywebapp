package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.UserRepository;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ResourceController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/registry" )
    public String addUserRegistry(Model model) {

        model.addAttribute("user",new User());

        return "addUser"; //renvoit à la page HTML du même nom
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        userService.addUser(user);

        return "register_success";//renvoit à la page HTML du même nom
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        Iterable<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);

        return "usersList";
    }


//    @RequestMapping("/*")
//    @RolesAllowed("USER")
//    public String getUser()
//    {
//        return "Welcome User";
//    }
//
//    @RequestMapping("/admin")
//    @RolesAllowed("ADMIN")
//    public String getAdmin()
//    {
//        return "Welcome Admin";
//    }

}
