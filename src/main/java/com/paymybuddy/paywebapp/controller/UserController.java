package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.UserRepository;
import com.paymybuddy.paywebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/user")
    public User addNewUser(@RequestParam String email,@RequestParam String password, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String description, @RequestParam Float balance) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setDescription(description);
        user.setBalance(balance);

        return userService.addNewUser(user);
    }

    @GetMapping(path = "/user")
    public @ResponseBody Iterable <User> getAllUsers() {
        return userService.getAllUsers();
    }
}
