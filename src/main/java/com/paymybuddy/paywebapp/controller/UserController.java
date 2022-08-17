package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.UserRepository;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
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

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user")
    public Optional<User> getUserById(@RequestParam(value = "id") Integer id) {
        return userService.getUserById(id);
    }
}
